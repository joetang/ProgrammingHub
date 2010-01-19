<?php
	require_once 'php/facebook.php';
	$appapikey = '<API_KEY>';
	$appsecret = '<SECRET>';
	$facebook = new Facebook($appapikey, $appsecret);
	$user_id = $facebook->require_login();
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:fb="http://www.facebook.com/2008/fbml">
	<head>
		<!-- css -->
	</head>
	<body>
	<script src="http://static.ak.connect.facebook.com/js/api_lib/v0.4/FeatureLoader.js.php" type="text/javascript"></script>
	<script>
		FB_RequireFeatures(["XFBML"], function(){ FB.Facebook.init("<?php echo $appapikey; ?>", "/xd_receiver.htm"); });
		function permitAll() {
			FB.Connect.showPermissionDialog("read_stream, publish_stream", function(perms) {
				alert(1);
			});
		}
	</script>
	<?php
		$user = $facebook->api_client->users_getInfo($user_id, 'uid,name,sex,profile_url,pic,pic_small,pic_big,pic_square');

		echo "<p>Hello, <fb:name uid=\"$user_id\" useyou=\"false\" />!</p>";
		echo "<img src='".$user[0]['pic_square']."'><br>";
		echo $user[0]['name']." (".$user[0]['uid'].")";


		echo "<p>Friends:<br>";

		$tfriends = $facebook->api_client->fql_query("SELECT uid2 FROM friend WHERE uid1= ".$user_id." AND uid2 IN (SELECT uid2 FROM friend WHERE uid1= ".$user_id." LIMIT 10 ) ORDER BY rand() limit 5");
		$i=0;
		foreach ($tfriends as $fd){
			$friends[$i++] = $fd['uid2'];
		}

		foreach ($friends as $friend) {
			$fd = $facebook->api_client->users_getInfo($friend, 'uid,name,sex,profile_url,pic_square');
			if($fd[0]['pic_square']!=""){
				echo "<img src='".$fd[0]['pic_square']."'>";
			}
			echo "<br>";
			echo "<a href='".$fd[0]['profile_url']."'>".$fd[0]['name']."</a> (".$fd[0]['uid'].")<br>";	
		}
		echo "</p>";
	?>
		<input type="button" value="click" onclick="permitAll()"/>
		<input type="button" value="test" onclick="FB.Connect.showFeedDialog()"/>
		<input type="button" value="test1" onclick="FB.Connect.inviteConnectUsers()"/>
		<a href="http://apps.facebook.com/myfirstapp_larry/push_stream.php" target="_parent">Push</a>
		<a href="http://apps.facebook.com/myfirstapp_larry/invite.php" target="_parent">Inv</a>
	</body>
</html>