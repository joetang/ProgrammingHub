<?php

class Db_class {
	var $query_num = 0;
	var $link;

	function Db_class($dbhost, $dbuser, $dbpw, $dbname, $pconnect = 0) {
		$this->connect($dbhost, $dbuser, $dbpw, $dbname, $pconnect);
	}

	function connect($dbhost, $dbuser, $dbpw, $dbname, $pconnect = 0) {
        global $dbcharset;
        $func = empty($pconnect) ? "mysql_connect" : "mysql_pconnect";
        if(!$this->link = @$func($dbhost, $dbuser, $dbpw, 1)) {
        	$this->halt("Can not connect to MySQL server");
        }
        if($this->server_info() > '4.1' && $dbcharset)
			mysql_query("SET NAMES '" . $dbcharset . "'", $this->link);
		if($this->server_info() > '5.0')
			mysql_query("SET sql_mode=''", $this->link);
		if($dbname) {
			if (!@mysql_select_db($dbname, $this->link)) $this->halt('Cannot use database '.$dbname);
		}
	}

	function select_db($dbname) {
		$this->dbname = $dbname;
		if (!@mysql_select_db($dbname, $this->link)) 
			$this->halt('Cannot use database '.$dbname);
	}

	function server_info() {
		return mysql_get_server_info();
	}
	
	function version() {
		return mysql_get_server_info();
	}

	function query($SQL, $method = '') {
        if($method == 'unbuffer' && function_exists('mysql_unbuffered_query')) 
			$query = mysql_unbuffered_query($SQL, $this->link);
		else
			$query = mysql_query($SQL, $this->link);
		if (!$query && $method != 'SILENT') 
            $this->halt('MySQL Query Error: ' . $SQL);
        $this->query_num++;
        //echo $SQL.'<br />';
		return $query;
	}
	
	function update($SQL) {
		return $this->query($SQL, 'unbuffer');
	}
	
    function get_value($SQL, $result_type = MYSQL_NUM) {
        $query = $this->query($SQL,'unbuffer');
        $rs =& mysql_fetch_array($query, MYSQL_NUM);
        return $rs[0];
    }

	function get_one($SQL) {
		$query = $this->query($SQL,'unbuffer');
		$rs =& mysql_fetch_array($query, MYSQL_ASSOC);
		return $rs;
	}
	
    function get_all($SQL, $result_type = MYSQL_ASSOC) {
        $query = $this->query($SQL);
        while($row = mysql_fetch_array($query, $result_type)) $result[] = $row;
        return $result;
    }

    function fetch_array($query, $result_type = MYSQL_ASSOC) {
        return mysql_fetch_array($query, $result_type);
    }

	function affected_rows() {
		return mysql_affected_rows($this->link);
	}

	function fetch_row($query) {
		return mysql_fetch_row($query);
	}

	function num_rows($query) {
		return mysql_num_rows($query);
	}

	function num_fields($query) {
		return mysql_num_fields($query);
	}

	function result($query, $row) {
		$query = mysql_result($query, $row);
		return $query;
	}

	function free_result($query) {
		return mysql_free_result($query);
	}

	function insert_id() {
		return ($id = mysql_insert_id($this->link)) >= 0 ? $id : $this->result($this->query("SELECT last_insert_id()"), 0);
	}

	function close() {
		return mysql_close($this->link);
	}

    function error() {
        return (($this->link) ? mysql_error($this->link) : mysql_error());
    }

    function errno() {
        return intval(($this->link) ? mysql_errno($this->link) : mysql_errno());
    }

    function select_query($fields, $table, $where) {
        if(!$fields) return;
        if(!$table) return;
        $where = $where ? "WHERE $where" : '';
        return $this->query("SELECT $fields FROM $table $where");
    }

    function select_one($fields, $table, $where) {
        if(!$fields) return;
        if(!$table) return;
        $where = $where ? "WHERE $where" : '';
        return $this->get_one("SELECT $fields FROM $table $where");
    }

    function select_all($fields, $table, $where) {
        if(!$fields) return;
        if(!$table) return;
        $where = $where ? "WHERE $where" : '';
        return $this->get_all("SELECT $fields FROM $table $where");
    }

    function select_value($field, $table, $where) {
        if(!$field) return;
        if(!$table) return;
        $where = $where ? "WHERE $where" : '';
        return $this->get_value("SELECT $field FROM $table $where");
    }

    function select_count($table, $where) {
        return $this->select_value("COUNT(*)", $table, $where);
    }

    function delete_new($table, $where) {
        if(!$table) return;
        $where = $where ? "WHERE $where" : '';
        return $this->query("DELETE FROM $table $where");        
    }

     function insert_new($table, $inlist) {
        if(!$table) return;
        if(!is_array($inlist) || count($inlist) == 0) return;
        foreach($inlist as $key => $val) {
            $set[] = "$key='$val'";
        }
        $SQL = "INSERT $table SET ".implode(", ", $set)." $where";
        return $this->query($SQL);
     }

    function update_new($table,$where,$uplist,$replace=0) {
        if(!$table) return;
        if(!is_array($uplist) || count($uplist) == 0) return;
        $where = $where ? "WHERE $where" : '';
        foreach($uplist as $key => $val) {
            $set[] = "$key='$val'";
        }
        if($replace) {
            $SQL = "REPLACE INTO %s SET %s";
        } else {
            $SQL = "UPDATE %s SET %s";
        }
        $SQL = sprintf($SQL, $table, implode(", ", $set)." $where");
        return $this->query($SQL);
    }

	function halt($msg = '') {
        global $charset;
		$message = "<html>\n<head>\n";
		$message .= "<meta content=\"text/html; charset=$charset\" http-equiv=\"Content-Type\">\n";
		$message .= "<style type=\"text/css\">\n";
		$message .=  "body,p,pre {\n";
		$message .=  "font:12px Verdana;\n";
		$message .=  "}\n";
		$message .=  "</style>\n";
		$message .= "</head>\n";
		$message .= "<body bgcolor=\"#FFFFFF\" text=\"#000000\" link=\"#006699\" vlink=\"#5493B4\">\n";

		$message .= "<p>数据库出错:</p><pre><b>".htmlspecialchars($msg)."</b></pre>\n";
		$message .= "<b>Mysql error description</b>: ".htmlspecialchars($this->error())."\n<br />";
		$message .= "<b>Mysql error number</b>: ".$this->errno()."\n<br />";
		$message .= "<b>Date</b>: ".date("Y-m-d @ H:i")."\n<br />";
		$message .= "<b>Script</b>: http://".$_SERVER['HTTP_HOST'].getenv("REQUEST_URI")."\n<br />";

		$message .= "</body>\n</html>";
		echo $message;
		exit;
	}
}
?>