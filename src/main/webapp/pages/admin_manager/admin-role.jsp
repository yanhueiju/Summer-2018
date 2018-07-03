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
<title>角色管理</title>
</head>
<body onload="opener.location.reload()">
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 管理员管理 <span class="c-gray en">&gt;</span> 角色管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20"> 
		<span class="l">
			<a href="javascript:;" onclick="batchDeletes()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
			<a class="btn btn-primary radius" data-title="添加角色" data-href="pages/admin_manager/admin-role-add.jsp" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a>
			<a href="javascript:;" onclick="exportTo()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 导出到EXCEL</a>
		</span> 
		<span class="r">共有数据：<strong>${roleCount}</strong> 条</span> </div>
			
	<%-- <div class="cl pd-5 bg-1 bk-gray"> <span class="l"> <a href="javascript:;" onclick="batchDeletes()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> <a class="btn btn-primary radius" href="javascript:;" onclick="admin_role_add('添加角色','pages/admin_manager/admin-role-add.jsp','800')"><i class="Hui-iconfont">&#xe600;</i> 添加角色</a> </span> <span class="r">共有数据：<strong>${roleCount}</strong> 条</span> </div>
	 --%><table class="table table-border table-bordered table-hover table-bg">
		<thead>
			<tr>
				<th scope="col" colspan="6">角色管理</th>
			</tr>

			<tr class="text-c">
				<th width="25"><input type="checkbox" value="" name=""></th>
				<th width="40">ID</th>
				<th width="200">角色名</th>
				<th>用户列表</th>
				<th width="300">描述</th>
				<th width="70">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${roles}" var="role">
				<tr class="text-c">
					<td><input type="checkbox" value="${role.roleId}" name="subcheck" id="subcheck"></td>
					<td>${role.roleId}</td>
					<td>${role.roleName}</td>
					<td><a href="#">${role.roleList}</a></td>
					<td>${role.roleDescription}</td>
					<td class="f-14"><a title="编辑" href="javascript:;" onclick="admin_role_edit('角色编辑','role/findRoleById.action?id=${role.roleId}','1')" style="text-decoration:none"><i class="Hui-iconfont">&#xe6df;</i></a> <a title="删除" href="javascript:;" onclick="Delete()" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
			</c:forEach>
			
		</tbody>
	</table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript">
/*管理员-角色-添加*/
function admin_role_add(title,url,w,h){
	layer_show(title,url,w,h);
}
/*管理员-角色-编辑*/
function admin_role_edit(title,url,id,w,h){
	layer_show(title,url,w,h);
}
/*管理员-角色-删除*/
/* function admin_role_del(obj,id){
	layer.confirm('角色删除须谨慎，确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: "role/deleteRoleById.action",
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
} */
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
                    url:"role/deleteRoleById.action",
                    data:{"delitems":checkedList.toString()},
                    datatype:"html",
                    success:function(data){
                        $("[name='checkbox2']:checkbox").attr("checked",false);
                        layer.msg('删除成功!',{icon:1,time:1000});
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
                 url:"role/batchDeletes.action",
                 data:{"delitems":checkedList.toString()},
                 datatype:"html",
                 success:function(data){
                     $("[name='checkbox2']:checkbox").attr("checked",false);
                     layer.msg('删除成功!',{icon:1,time:1000});
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
                 url:"role/toExcel.action",
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