function objectEquals(obj1, obj2) {
    for (var i in obj1) {
        if (obj1.hasOwnProperty(i)) {
            if (!obj2.hasOwnProperty(i)) return false;
            if (obj1[i] != obj2[i]) return false;
        }
    }
    for (var i in obj2) {
        if (obj2.hasOwnProperty(i)) {
            if (!obj1.hasOwnProperty(i)) return false;
            if (obj1[i] != obj2[i]) return false;
        }
    }
    return true;
}
jQuery.fn.validateRowSum = function(options) {
	var options = jQuery.extend( {
		show_error_alert: false,
		show_correct_alert: false,
		error_class: 'validateError',
		correct_class: 'validateCorrect',
		debug_highlight_class: 'validateDebug',
		overide_error_class: false,
		debug: false,
		start_td: -1, // not implemented
		end_td: -1 // not implemented
	},options);
	$(this).each(function(){
		var rowSum = 0;
		var validateRowSum = $(this);
		var tdCell = 0;
		$(this).parent().children().each(function(){
			if(objectEquals(validateRowSum, $(this))){
				if(parseInt($(this).html())==parseInt(rowSum)){
					if(!$(this).hasClass(options.error_class) || options.overide_error_class){
						$(this).addClass(options.correct_class);
					}
					if(options.show_correct_alert){
						alert("Row sum is correct");
					}
					return false;
				}else{
					$(this).addClass(options.error_class);
					if(options.show_error_alert){
						alert("Row sum is not correct! TOTAL: "+$(this).html()+" ROW SUM: "+rowSum);
					}
				}
			}else{
				if(!isNaN($(this).html()) && $(this).html().length!=0){
					if(options.debug){
						$(this).addClass(options.debug_highlight_class);
						alert("rowSum="+rowSum+" "+parseInt($(this).html()));
						$(this).removeClass(options.debug_highlight_class);
					}
					rowSum+=parseInt($(this).html());
				}else{
					if(options.debug){
							$(this).addClass(options.debug_highlight_class);
							alert("This is not a integer.");
							$(this).removeClass(options.debug_highlight_class);
					}
				}
			}
		});
	});
};
jQuery.fn.validateColumnSum = function(options) {
	var options = jQuery.extend( {
		show_error_alert: false,
		show_correct_alert: false,
		error_class: 'validateError',
		correct_class: 'validateCorrect',
		debug_highlight_class: 'validateDebug',
		overide_error_class: false,
		debug: false,
		start_tr: -1, // not implemented
		end_tr: -1 // not implemented
	},options);
	$(this).each(function(){
		var tdCell = 0;
		var validateColumnSum = $(this);
		$(this).parent().children().each(function(){
			if(!objectEquals(validateColumnSum, $(this))){
				tdCell++;
			}else{
				return false;
			}
		});
		var columnSum = 0;
		$(this).parent().parent().children().each(function(){
			var i = 0;
			$(this).children().each(function(){
				if(i==tdCell){
					if(objectEquals(validateColumnSum, $(this))){
						if(parseInt($(this).html())==parseInt(columnSum)){
							if(!$(this).hasClass(options.error_class) || options.overide_error_class){
								$(this).addClass(options.correct_class);
							}
							if(options.show_correct_alert){
								alert("Column sum is correct");
							}
							return false;
						}else{
							$(this).addClass(options.error_class);
							if(options.show_error_alert){
								alert("Column sum is not correct! TOTAL: "+$(this).html()+" COLUMN SUM: "+columnSum);
							}
						}
					}else{
						if(!isNaN($(this).html()) && $(this).html().length!=0){
							if(options.debug){
								$(this).addClass(options.debug_highlight_class);
								alert("columnSum="+columnSum+"+"+parseInt($(this).html())+"="+(columnSum+parseInt($(this).html())));
								$(this).removeClass(options.debug_highlight_class);
							}
							columnSum+=parseInt($(this).html());
						}else{
							if(options.debug){
								$(this).addClass(options.debug_highlight_class);
								alert("This is not a integer.");
								$(this).removeClass(options.debug_highlight_class);
							}
						}
					}
					i++;
				}else{
					i++;
				}
			});
		});
	});
};