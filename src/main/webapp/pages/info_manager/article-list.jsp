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
		<title>信息发布列表</title>
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
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 信息管理 <span class="c-gray en">&gt;</span> 信息发布管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
		
		<div class="page-container">
		<form action="info/fuzzyInfo.action">
			<div class="text-c">
				<span class="select-box inline">
					<select name="info_category" class="select">
						<option value="ALL">全部分类</option>
						<option value="Notification">通知公告</option>
						<option value="Policy">政策速递</option>
						<option value="Tax">纳税指导</option>
					</select>
		
				</span> 
				日期范围：
				 <input type="text" name="info_date" class="input-text Wdate" placeholder="选择起止时间" id="test10" style="width:320px;">
					&nbsp;&nbsp;&nbsp;
				 <input type="text" name="info_title" id="" placeholder="信息标题" style="width:250px" class="input-text">
				<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜信息</button>
			</div>
		</form>
			<div class="cl pd-5 bg-1 bk-gray mt-20">
			 <span class="l">
			 	<a href="javascript:;" onclick="batchDeletes()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
			 	<a class="btn btn-primary radius" data-title="添加信息" data-href="pages/info_manager/article-add.jsp" onclick="Hui_admin_tab(this)" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加信息</a>
			 	<a href="javascript:;" onclick="exportTo()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 导出到EXCEL</a>
			 </span> 
			 <span class="r">共有数据：<strong>${infoCount}</strong> 条</span> 
			 </div>
			<div class="mt-20">
				<table class="table table-border table-bordered table-bg table-hover table-sort">
					<thead>
						<tr class="text-c">
							<th width="25"><input type="checkbox" name="" value=""></th>
							<th>ID</th>
							<th>标题</th>
							<th>信息分类</th>
							<th>创建人</th>
							<th>创建时间</th>
							<th>发布状态</th>
							<th width="120">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${infos}" var="info">
							<tr class="text-c">
								<td><input type="checkbox" value="${info.info_id}" name="subcheck"></td>
								<td>${info.info_id}</td>
								<td>${info.info_title}</td>
								<td>${info.info_category}</td>
								<td>${admin.admin_name }</td>
								<td>${info.info_time}</td>
								
								
								<c:if test="${info.info_status == 0}"> 
									<td class="td-status"><span class="label label-defaunt radius">已下架</span></td>
								</c:if> 
								
								<c:if test="${info.info_status == 1}"> 
									<td class="td-status"><span class="label label-success radius">已发布</span></td>
								</c:if>

								<td class="f-14 td-manage">
									<a style="text-decoration:none" onClick="article_stop(this,'10001')" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>
									<a style="text-decoration:none" class="ml-5" onClick="article_edit('信息编辑','info/findInfoById.action?id=${info.info_id}','10001')" href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
									<a style="text-decoration:none" class="ml-5" onClick="Delete()" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
								</td>
							</tr>
						</c:forEach>
						
					</tbody>
				</table>
			</div>
		</div>
		<!--_footer 作为公共模版分离出去-->
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
		<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
		<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
		<!--/_footer 作为公共模版分离出去-->

		<!--请在下方写此页面业务相关的脚本-->
		<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
		<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
		<script type="text/javascript">
			/*信息-添加*/
			function article_add(title, url, w, h) {
				var index = layer.open({
					type: 2,
					title: title,
					content: url
				});
				layer.full(index);
			}
			/*信息-编辑*/
			function article_edit(title, url, id, w, h) {
				var index = layer.open({
					type: 2,
					title: title,
					content: url
				});
				layer.full(index);
			}
			/*信息-删除*/
			function article_del(obj, id) {
				layer.confirm('确认要删除吗？', function(index) {
					$.ajax({
						type: 'POST',
						url: '',
						dataType: 'json',
						success: function(data) {
							$(obj).parents("tr").remove();
							layer.msg('已删除!', {
								icon: 1,
								time: 1000
							});
						},
						error: function(data) {
							console.log(data.msg);
						},
					});
				});
			}

			/*信息-下架*/
			function article_stop(obj, id) {
				layer.confirm('确认要下架吗？', function(index) {
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
					$(obj).remove();
					layer.msg('已下架!', {
						icon: 5,
						time: 1000
					});
				});
			}
		
			
			/*信息-发布*/
			function article_start(obj, id) {
				layer.confirm('确认要发布吗？', function(index) {
					$(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
					$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
					$(obj).remove();
					layer.msg('已发布!', {
						icon: 6,
						time: 1000
					});
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
                    url:"info/deleteInfoById.action",
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
                 url:"info/batchDeletes.action",
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
                 url:"info/toExcel.action",
                 data:{"delitems":checkedList.toString()},
                 datatype:"html",
                 success:function(data){
                	 /* $("[name='checkbox2']:checkbox").attr("checked",false);
                     location.reload();//页面刷新 */
                	 layer.msg('导出成功!',{icon:1,time:1000});
                	 $("[name='checkbox2']:checkbox").attr("checked",false);
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