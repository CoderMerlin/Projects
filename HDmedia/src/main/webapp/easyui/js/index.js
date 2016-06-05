 $(function(){
	 $('#index_content').tabs('add',{
		title:'弘道科技信息',
		selected:true,
		closable:false,
		href:"back/HDmedia.jsp"
	 });
	 
	 $("#menu-tree").tree({
		onClick:function(node){
			var obj=$('#index_content');
			if(node.id=="guanli_manager"){  //说明是管理员信息管理
				//判断管理员信息管理界面是否打开
				if(obj.tabs("exists","管理员信息管理")){  //则选中
					obj.tabs("select","管理员信息管理");
				}else{
					obj.tabs('add',{
						title:'管理员信息管理',
						closable:true,
						fit:true,
						href:"back/guanli.jsp"
						});
				}
			}else if(node.id=="yonghu_manager"){  //说明是用户注册信息管理
				//判断会员信息是否打开
				if(obj.tabs("exists","用户注册信息管理")){  //则选中
					obj.tabs("select","用户注册信息管理");
				}else{
					obj.tabs('add',{
						title:'用户注册信息管理',
						closable:true,
						fit:true,
						href:"back/yonghuzc.jsp"
					});
				}
			}else if(node.id=="yonghu_more_manger"){  //说明是用户注册信息管理
				//判断会员信息是否打开
				if(obj.tabs("exists","用户详细信息管理")){  //则选中
					obj.tabs("select","用户详细信息管理");
				}else{
					obj.tabs('add',{
						title:'用户详细信息管理',
						closable:true,
						fit:true,
						href:"back/yonghu.jsp"
					});
				}
			}else if(node.id=="search_yonghu"){  //说明是查询用户信息
				//判断会员信息是否打开
				if(obj.tabs("exists","用户详细信息查询")){  //则选中
					obj.tabs("select","用户详细信息查询");
				}else{
					obj.tabs('add',{
						title:'用户详细信息查询',
						closable:true,
						fit:true,
						href:"back/yonghusearch.jsp"
					});
				}
			}else if(node.id=="change_pwd"){  //说明是修改密码
				//判断修改密码是否打开
				    var pwd=prompt("请输入您的密码","");
				    if (pwd!=null && pwd!="") {
				       $.post("../loginServlet?d="+new Date(),{op:"checkadminpwd",pwd:pwd},function(data){
				    	   if(data==1){ //密码是正确的
				    		   $("#changepwd").dialog("open");
				    	   }else{
				    		   alert("密码错误！");
				    	   }
				       });
				}
			}else if(node.id=="diyu"){  //说明是地域管理
				//判断地域管理是否打开
				if(obj.tabs("exists","地域管理")){  //则选中
					obj.tabs("select","地域管理");
				}else{
					obj.tabs('add',{
						title:'地域管理',
						closable:true,
						fit:true,
						href:"back/diyu.jsp"
					});
				}
			}else if(node.id=="minzu"){  //说明是民族导航
				//判断民族导航是否打开
				if(obj.tabs("exists","民族导航")){  //则选中
					obj.tabs("select","民族导航");
				}else{
					obj.tabs('add',{
						title:'民族导航',
						closable:true,
						fit:true,
						href:"back/minzu.jsp"
					});
				}
			}else if(node.id=="diyu_pic"){  //说明是地域图片管理
				//判断地域图片管理是否打开
				if(obj.tabs("exists","地域图片管理")){  //则选中
					obj.tabs("select","地域图片管理");
				}else{
					obj.tabs('add',{
						title:'地域图片管理',
						closable:true,
						fit:true,
						href:"back/diyu_pic.jsp"
					});
				}
			}else if(node.id=="minzu_pic"){  //说明是民族图片管理
				//判断民族图片管理是否打开
				if(obj.tabs("exists","民族图片管理")){  //则选中
					obj.tabs("select","民族图片管理");
				}else{
					obj.tabs('add',{
						title:'民族图片管理',
						closable:true,
						fit:true,
						href:"back/minzu_pic.jsp"
					});
				}
			}
			
		} 
	 });
	 
	 //论坛管理
	 $("#notice-tree").tree({
		 onClick:function(node){
			var obj=$('#index_content');
		    if(node.id=="notice_browse"){  //说明是浏览公告
				//判断浏览公告是否打开
				if(obj.tabs("exists","浏览公告")){  //则选中
					obj.tabs("select","浏览公告");
				}else{
					obj.tabs('add',{
						title:'浏览公告',
						closable:true,
						fit:true,
						href:"back/noticebrowse.jsp"
					});
				}
			}else if(node.id=="notice_operate"){  //说明是公告操作
				//判断公告操作是否打开
				if(obj.tabs("exists","公告操作")){  //则选中
					obj.tabs("select","公告操作");
				}else{
					obj.tabs('add',{
						title:'公告操作',
						closable:true,
						fit:true,
						href:"back/noticeoperate.jsp"
					});
				}
			}else if(node.id=="noticeclass_operate"){  //说明是论坛栏目操作
				//判断论坛栏目操作是否打开
				if(obj.tabs("exists","论坛栏目操作")){  //则选中
					obj.tabs("select","论坛栏目操作");
				}else{
					obj.tabs('add',{
						title:'论坛栏目操作',
						closable:true,
						fit:true,
						href:"back/noticeclassoperate.jsp"
					});
				}
			}else if(node.id=="invitation_browse"){  //说明是浏览帖子
				//判断浏览帖子是否打开
				if(obj.tabs("exists","浏览帖子")){  //则选中
					obj.tabs("select","浏览帖子");
				}else{
					obj.tabs('add',{
						title:'浏览帖子',
						closable:true,
						fit:true,
						href:"back/invitationbrowse.jsp"
					});
				}
			}else if(node.id=="invitation_operate"){  //说明是帖子操作
				//判断帖子操作是否打开
				if(obj.tabs("exists","帖子操作")){  //则选中
					obj.tabs("select","帖子操作");
				}else{
					obj.tabs('add',{
						title:'帖子操作',
						closable:true,
						fit:true,
						href:"back/invitationoperate.jsp"
					});
				}
			}
		 }
	 });
	 
	//文章管理
	 $("#article-tree").tree({
		 onClick:function(node){
		 	var obj=$('#index_content');
		 	if(node.id=="article_type"){  //说明是文章类型管理
				//判断浏览文章是否打开
				if(obj.tabs("exists","文章类型管理")){  //则选中
					obj.tabs("select","文章类型管理");
				}else{
					obj.tabs('add',{
						title:'文章类型管理',
						closable:true,
						fit:true,
						href:"back/articletype.jsp"
					});
				}
			}else if(node.id=="article_browse"){  //说明是浏览文章
				//判断浏览文章是否打开
				if(obj.tabs("exists","浏览文章")){  //则选中
					obj.tabs("select","浏览文章");
				}else{
					obj.tabs('add',{
						title:'浏览文章',
						closable:true,
						fit:true,
						href:"back/articlebrowse.jsp"
					});
				}
			}else if(node.id=="article_type"){  //说明是浏览文章
				//判断浏览文章是否打开
				if(obj.tabs("exists","文章类型管理")){  //则选中
					obj.tabs("select","文章类型管理");
				}else{
					obj.tabs('add',{
						title:'文章类型管理',
						closable:true,
						fit:true,
						href:"back/articletype.jsp"
					});
				}
			}else if(node.id=="article_operate"){  //说明是文章操作
				//判断文章操作是否打开
				if(obj.tabs("exists","文章操作")){  //则选中
					obj.tabs("select","文章操作");
				}else{
					obj.tabs('add',{
						title:'文章操作',
						closable:true,
						fit:true,
						href:"back/articleoperate.jsp"
					});
				}
			}else if(node.id=="dt_dietary"){  //说明是饮食文化管理
				//判断一级导航是否打开
				if(obj.tabs("exists","饮食文化")){  //则选中
					obj.tabs("select","饮食文化");
				}else{
					obj.tabs('add',{
						title:'饮食文化',
						closable:true,
						fit:true,
						href:"back/dietary.jsp"
					});
				}
			}
		 }
	 });
	 
	 //作者及作品管理
	 $("#authorandwork-tree").tree({
		 onClick:function(node){
			 var obj=$('#index_content');
			 if(node.id=="author_browse"){  //说明是作者管理
				//判断浏览公告是否打开
				if(obj.tabs("exists","作者管理")){  //则选中
					obj.tabs("select","作者管理");
				}else{
					obj.tabs('add',{
						title:'作者管理',
						closable:true,
						fit:true,
						href:"back/author.jsp"
					});
				}
			 }else if(node.id=="works_operate"){  //说明是帖子操作
				//判断帖子操作是否打开
				if(obj.tabs("exists","作品操作")){  //则选中
					obj.tabs("select","作品操作");
				}else{
					obj.tabs('add',{
						title:'作品操作',
						closable:true,
						fit:true,
						href:"back/works.jsp"
					});
				}
			 }
		 }
	 });	 
	 //前台界面管理
	 $("#qtstage-tree").tree({
		 onClick:function(node){
			 var obj=$('#index_content');
			 if(node.id=="hd_nav_one"){  //说明是一级导航
					//判断一级导航是否打开
					if(obj.tabs("exists","一级导航管理")){  //则选中
						obj.tabs("select","一级导航管理");
					}else{
						obj.tabs('add',{
							title:'一级导航管理',
							closable:true,
							fit:true,
							href:"back/hdnavone.jsp"
						});
					}
			  }else if(node.id=="hd_nav_two"){  //说明是二级导航
					//判断一级导航是否打开
					if(obj.tabs("exists","二级导航管理")){  //则选中
						obj.tabs("select","二级导航管理");
					}else{
						obj.tabs('add',{
							title:'二级导航管理',
							closable:true,
							fit:true,
							href:"back/hdnavtwo.jsp"
						});
					}
			  }else if(node.id=="geography"){  //说明是二级导航
					//判断一级导航是否打开
					if(obj.tabs("exists","地域")){  //则选中
						obj.tabs("select","地域");
					}else{
						obj.tabs('add',{
							title:'地域',
							closable:true,
							fit:true,
							href:"back/geography.jsp"
						});
					}
			  }
		 }
	 });
	 
	 
	 //公告管理
	 $("#gonggao-tree").tree({
		 onClick:function(node){
			 var obj=$('#index_content');
			 if(node.id=="notice_operate"){
				//判断浏览公告是否打开
				if(obj.tabs("exists","公告操作")){  //则选中
					obj.tabs("select","公告操作");
				}else{
					obj.tabs('add',{
						title:'公告操作',
						closable:true,
						fit:true,
						href:"back/noticeoperate.jsp"
					});
				}
			 }
		 }
	 });	 
	 
	 
 });
 
    
 
 
 
 
 
//注销管理员
 function loginOut(){
 	$.post("../loginServlet?d="+new Date(),{op:"adminloginout"},function(data){
 		if(data==1){//成功
 			location.href='../login.jsp';
 		}else{//说明失败
 			alert("注销失败....");
 		}
 	});

 }
 
 //修改管理员密码
 function changeadminpwd(){
	 var pwd=$.trim($("#changepwda").val());
	 var rpwd=$.trim($("#rchangepwd").val());
	 if(pwd==rpwd){
		 $.post("../loginServlet?d="+new Date(),{op:"changeadminpwd",pwd:pwd},function(data){
			 if(data==1){//成功
		 			alert("修改成功...");
		 			$("#changepwd").dialog("close");
					$("#news_info").datagrid("reload");
		 		}else{//说明失败
		 			alert("注销失败....");
		 			$("#changepwd").dialog("close");
		 	 }
		 });
	 }
 }
 

 