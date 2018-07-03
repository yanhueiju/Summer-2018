<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	//通过JSP的 代码，在浏览器端来获取 java后台访问的根URL
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>

<head>
<!--  在head标签中，添加一个 当前界面访问工程的根 路径  -->
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>管理员列表</title>
<script src="laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script>
    lay('#version').html('-v'+ laydate.v);

    //日期时间范围
    laydate.render({
      elem: '#test10'
      ,type: 'datetime'
      ,range: true
    }); 

</script>

</head>
<body onload="opener.location.reload()">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 管理员列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<form action="admin/fuzzyInfo.action">
	<div class="text-c"> 日期范围：
		<!-- <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}' })" id="datemin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d' })" id="datemax" class="input-text Wdate" style="width:120px;">
		 -->
		 <input type="text" name="info_date" class="input-text Wdate" placeholder="选择起止时间" id="test10" style="width:320px;">
		&nbsp;&nbsp;&nbsp;
		<input name="likeName" type="text" class="input-text" style="width:250px" placeholder="输入管理员名称" id="" >
		<button type="submit" class="btn btn-success" id="" name=""><i class="Hui-iconfont">&#xe665;</i> 搜用户</button>
	
	</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	 <span class="l">
	 	<a href="javascript:;" onclick="batchDeletes()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
	 	<a class="btn btn-primary radius" data-title="添加管理员" data-href="pages/admin_manager/admin-add.jsp" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加管理员</a>
	 	<a href="javascript:;" onclick="exportTo()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 导出到EXCEL</a>
		
	 </span> 
	 <span class="r">共有数据：<strong>${adminCount}</strong> 条</span> </div>
	
	<%-- <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="batchDeletes()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a href="javascript:;" onclick="admin_add('添加管理员','pages/admin_manager/admin-add.jsp','800','500')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加管理员</a></span> <span class="r">共有数据：<strong>${adminCount}</strong> 条</span> </div>
	 --%><table class="table table-border table-bordered table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="9">员工列表</th>
			</tr>
			<tr class="text-c">
				<th width="25"><input type="checkbox" name="" value=""></th>
				<th width="40">ID</th>
				<th width="150">登录名</th>
				<th width="90">手机</th>
				<th width="150">邮箱</th>
				<th>角色</th>
				<th width="130">加入时间</th>
				<th width="100">是否已启用</th>
				<th width="100">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${user.type =='admin'}">
				<c:forEach items="${admins}" var="admin">
				<tr class="text-c">
					<td><input type="checkbox" value="${admin.admin_id}" name="subcheck"></td>
					<td>${admin.admin_id}</td>
					<td>${admin.admin_name}</td>
					<td>${admin.admin_phone}</td>
					<td>${admin.admin_email}</td>
					<td>${admin.admin_role}</td>
					<td>${admin.ctime}</td>
					<td class="td-status"><span class="label label-success radius">已启用</span></td>
					<td class="td-manage"><a style="text-decoration:none" onClick="admin_stop(this,'10001')" href="javascript:;" title="停用"><i class="Hui-iconfont">&#xe631;</i></a> <a title="编辑" href="javascript:;" onclick="admin_edit('管理员编辑','admin/findAdminById.action?id=${admin.admin_id}','1','800','500')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="Delete()" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${user.type =='role'}">
			<tr class="text-c">
				<th scope="col" colspan="9">该内容非管理员用户不可见</th>
			</tr>
			</c:if>
			
		</tbody>
	</table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> 
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: "admin/delete.action",
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
	layer.confirm('确认要停用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
		$(obj).remove();
		layer.msg('已停用!',{icon: 5,time:1000});
	});
}

/*管理员-启用*/
function admin_start(obj,id){
	layer.confirm('确认要启用吗？',function(index){
		//此处请求后台程序，下方是成功后的前台处理……
		
		
		$(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
		$(obj).remove();
		layer.msg('已启用!', {icon: 6,time:1000});
	});
}
</script>

<script type="text/javascript">
		 function Delete(){
                //判断至少写了一项
                var checkedNum = $("input[name='subcheck']:checked").length;
                if(checkedNum==0){
                    alert("请至少选择一项!");
                    return false;
                }
                if(confirm("确定删除所选项目?")){
                var checkedList = new Array();
                $("input[name='subcheck']:checked").each(function(){
                    checkedList.push($(this).val());
                });
                $.ajax({
                    type:"POST",
                    url:"admin/deleteAdminById.action",
                    data:{"delitems":checkedList.toString()},
                    datatype:"html",
                    success:function(data){
                    	layer.msg('删除成功!',{icon:1,time:1000});
                    	$("[name='checkbox2']:checkbox").attr("checked",false); 
                        location.reload();//页面刷新
                    },
                    error:function(data){
                        art.dialog.tips('删除失败!');
                    }
                });
               }
        }
		 
		 function batchDeletes(){
             //判断至少写了一项
             var checkedNum = $("input[name='subcheck']:checked").length;
             if(checkedNum==0){
                 alert("请至少选择一项!");
                 return false;
             }
             if(confirm("确定删除所选项目?")){
             var checkedList = new Array();
             $("input[name='subcheck']:checked").each(function(){
                 checkedList.push($(this).val());
             });
             $.ajax({
                 type:"POST",
                 url:"admin/batchDeletes.action",
                 data:{"delitems":checkedList.toString()},
                 datatype:"html",
                 success:function(data){
                	 layer.msg('删除成功!',{icon:1,time:1000});
                     $("[name='checkbox2']:checkbox").attr("checked",false);
                     location.reload();//页面刷新
                 },
                 error:function(data){
                     art.dialog.tips('删除失败!');
                 }
             });
            }
     }
		 function exportTo(){
             //判断至少写了一项
             var checkedNum = $("input[name='subcheck']:checked").length;
            if(checkedNum==0){
                 alert("请至少选择一项!");
                 return false;
             }
             if(confirm("确定导出所选项目?")){
             var checkedList = new Array();
             $("input[name='subcheck']:checked").each(function(){
                 checkedList.push($(this).val());
             });
             $.ajax({
                 type:"POST",
                 url:"admin/toExcel.action",
                 data:{"delitems":checkedList.toString()},
                 datatype:"html",
                 success:function(data){
                	 /* $("[name='checkbox2']:checkbox").attr("checked",false);
                     location.reload();//页面刷新 */
                	 layer.msg('导出成功!',{icon:1,time:1000});
                 },
                 error:function(data){
                     art.dialog.tips('导出失败!');
                 }
             });
            }
     }	 	 
	</script>
	<script>
		window.opener.document.location.reload();	 
	</script>

</body>
</html>