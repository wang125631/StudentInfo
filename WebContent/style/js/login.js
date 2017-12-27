// JavaScript Document
//鏀寔Enter閿櫥褰�
//		document.onkeydown = function(e){
//			if($(".bac").length==0)
//			{
//				if(!e) e = window.event;
//				if((e.keyCode || e.which) == 13){
//					var obtnLogin=document.getElementById("submit_btn");
//					obtnLogin.focus();
//				}
//			}
//		};

    	$(function(){
			//鎻愪氦琛ㄥ崟
			$('#submit_btn').click(function(){
				var l_index = layer.load('鐧诲綍涓�...');
				if($('#account').val() == ''){
					layer.msg('璇疯緭鍏ヨ处鍙凤紒',1);	
					$('#account').focus();
				}else if($('#password').val() == ''){
					layer.msg('璇疯緭鍏ュ瘑鐮侊紒',1);
					$('#password').focus();
				}else if($('#verifyDiv').css('display') != 'none' && $('#j_captcha').val() == ''){
					layer.msg('璇疯緭鍏ラ獙璇佺爜锛�',1);
					$('#j_captcha').focus();
				}else{
					//ajax鎻愪氦琛ㄥ崟锛�#login_form涓鸿〃鍗曠殑ID銆� 濡傦細$('#login_form').ajaxSubmit(function(data) { ... });
					$.ajax({
						url:"login!doLogin.action",
						type:"post",
						data:{
							account:$("#account").val(),
							pwd:$("#password").val(),
							verifycode:$("#j_captcha").val()
						},
						dataType:"json",
						complete:function(){
							layer.close(l_index);
						},
						success:function(result){
							if(result.status == 'y'){//鐧诲綍鎴愬姛
								layer.msg('鐧诲綍鎴愬姛  姝ｅ湪涓烘偍璺宠浆...',3,10);
								window.location.href = result.msg;
							}else{//鐧诲綍澶辫触
								changeVerifyCode();
								layer.alert(result.msg);
								isNeedVerify();
							}
						},
						error:function(){
							layer.alert("鐧诲綍瓒呮椂锛岃绋嶅悗閲嶈瘯锛�");
						}
					});
					
				}
			});
			
			
			//鍏ㄥ睆鍥�
		 	$.supersized({

			        // Functionality
			        slide_interval     : 4000,    // Length between transitions
			        transition         : 3,    // 0-None, 1-Fade, 2-Slide Top, 3-Slide Right, 4-Slide Bottom, 5-Slide Left, 6-Carousel Right, 7-Carousel Left
			        transition_speed   : 1000,    // Speed of transition
			        performance        : 1,    // 0-Normal, 1-Hybrid speed/quality, 2-Optimizes image quality, 3-Optimizes transition speed // (Only works for Firefox/IE, not Webkit)
			        
			        // Size & Position
			        min_width          : 0,    // Min width allowed (in pixels)
			        min_height         : 0,    // Min height allowed (in pixels)
			        vertical_center    : 1,    // Vertically center background
			        horizontal_center  : 1,    // Horizontally center background
			        fit_always         : 0,    // Image will never exceed browser width or height (Ignores min. dimensions)
			        fit_portrait       : 1,    // Portrait images will not exceed browser height
			        fit_landscape      : 0,    // Landscape images will not exceed browser width

			        // Components
			        slide_links        : 'blank',    // Individual links for each slide (Options: false, 'num', 'name', 'blank')
			        slides             : [    // Slideshow Images
											 {image : 'styles/images/customize/1.jpg'}
			                       ]

			    });   			
			
			
		});
    	
    	
