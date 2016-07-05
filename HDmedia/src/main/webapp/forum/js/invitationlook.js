	
function showHuiTie(tid){
	$.post("../huiTieServlet",{op:"selectpage",tid:tid},function(data){
       var str="";
       allpage=data.allpage;
       for(var i=1;i<=data.allpage;i++){
 		str+="<li><a class='page' href='javascript:findpage("+i+")'>"+i+"</a></li>";
 		}
         str+="<li><a class='next' href='javascript:next()' >></a></li>";
         str+="<li><a class='last' href='javascript:last()' > 末页>> </a></li>";
       $("#bc").append($(str));
       },"json"); 
	findpage(1,tid);	
}
var currentPage=1;
var allpage;

function findpage(num,tid){
	$.post("../huiTieServlet",{op:"findpage",num:num,tid:tid},function(data){
		if(data!=0){
			
			
		}else{
			console.info("查询数据异常....");
		}
	},"json");
	
}

function last(){
	findpage(allpage);
}

function next(){
	if(currentPage==allpage){
		findpage(allpage);
	}else{
		findpage(currentPage+1);
	}
}

function previous(){
	if(currentPage==1){
		findpage(1);
	}else{
		findpage(currentPage-1);
	}
}
               

function submitAnswer(){
	var content=ue.getContent();
	uno=$.trim(uno);
	if(uno=="" || uno==null || uno.length<1){
		alert("请先登录!!!");
	} else{
		$.post("../answerServlet",{op:"addAnswer",uno:uno,askno:askno,content:content},function(data){
			data=parseInt(data);
			if(data==1){
				location.reload(true);   
			}else{
				alert("内容不能为空!!!");
			}
		});
	}
	
}


