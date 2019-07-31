<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>餐厅</title>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
    <!-- 新 Bootstrap4 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
    <!-- popper.min.js 用于弹窗、提示、下拉菜单 -->
    <script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
    <!-- 最新的 Bootstrap4 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<body>
 <div class="container">
     <h1>餐厅管理</h1>
     <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addModal">添加</button>

     <!-- 模态框 -->
     <div class="modal fade" id="addModal">
         <div class="modal-dialog">
             <div class="modal-content">

                 <!-- 模态框头部 -->
                 <div class="modal-header">
                     <h4 class="modal-title">添加餐厅信息</h4>
                     <button type="button" class="close" data-dismiss="modal">&times;</button>
                 </div>

                 <!-- 模态框主体 -->
                 <div class="modal-body">
                     <form id="addForm" >
                         <div class="form-group">
                             <label>餐厅id</label>
                             <input type="number" name="restaurantId" class="form-control"/>
                         </div>
                         <br/>
                         <div class="form-group">
                             <label>名称</label>
                             <input type="text" name="name" class="form-control"/>
                         </div>
                         <br/>
                         <div class="form-group">
                             <label>地址</label>
                             <input type="text" name="address" class="form-control"/>
                         </div>
                         <br/>
                         <div class="form-group">
                             <label>详细信息</label>
                             <input type="text" name="description" class="form-control"/>
                         </div>
                         <br/>
                         <div class="form-group">
                             <label>店主账号</label>
                             <input type="number" name="masterId" class="form-control"/>
                         </div>
                     </form>
                 </div>

                 <!-- 模态框底部 -->
                 <div class="modal-footer">
                     <button type="button" class="btn btn-secondary"  onclick="addRest()">提交</button>
                     <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                 </div>

             </div>
         </div>
     </div>
     <br/>
     <br/>
     <form id="form" method="post">
         <table class="table" border="1">
             <thead>
             <tr>
                 <td>序号</td>
                 <td>名称</td>
                 <td>地址</td>
                 <td>描述</td>
                 <td>店主id</td>
                 <td>操作</td>
             </tr>
             </thead>
             <tbody>
             <c:forEach var="restaurant" items="${requestScope.restaurantList}">
                 <tr id="${restaurant.restaurantId}">
                     <td>${restaurant.restaurantId}</td>
                     <td>${restaurant.name}</td>
                     <td>${restaurant.address}</td>
                     <td>${restaurant.description}</td>
                     <td>${restaurant.masterId}</td>
                     <td>
                         <button type="button"  onclick="update(${restaurant.restaurantId})">修改</button>
                         <button type="button"  onclick="deleteRest(${restaurant.restaurantId})">删除</button>
                     </td>
                 </tr>
             </c:forEach>
             </tbody>
         </table>
     </form>
 </div>


</body>
<script>
    function addRest() {
        $.ajax({
            type:"post",
            datatype:"json",
            url:"addRestaurant",
            data:$("#addForm").serialize(),
            success:function (msg) {
                if(msg.success ==true){
                    location.reload();
                } else{
                    if(msg.message =="101") alert("餐厅id已存在");

                }
            },
            error:function (){
                alert("添加失败");
            }

        })

    }
    function update(restId){
        var element=document.getElementById(restId);
        element.innerHTML="<td><input type=\"number\" name=\"restaurantId\" value="+restId+" readonly/></td>" +
            "<td><input type=\"text\" name=\"name\" value=\"\"/></td>" +
            "<td><input type=\"text\" name=\"address\" value=\"\"/> </td>" +
            "<td><input type=\"text\" name=\"description\" value=\"\"/></td>" +
            "<td><input type=\"number\" name=\"masterId\" value=\"\" /></td>" +
            "<td><button id=\"btn1\" type=\"button\" onclick=\"submitRest()\">提交</button></td>";

    }
    function submitRest() {
        $.ajax(
            {
                type:"post",
                dataType:"json",
                url:"updateRestaurant",

                data:$("#form").serialize(),
                success:function (msg) {
                    if(msg.success==true) {

                        alert("修改成功");
                        location.reload();
                    }else{
                        alert("修改失败");
                        location.reload();
                    }
                },
                error:function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                    alert(jqXHR.status);
                    alert(jqXHR.readyState);
                    alert(jqXHR.statusText);
                    /*弹出其他两个参数的信息*/
                    alert(textStatus);
                    alert(errorThrown);
                }
            }
        )

    }
    function deleteRest(restId) {
        var ctx="${ctx}";
        var path="localhost:8080"+ctx+"/deleteRestaurant"
        //document.write(path+restId);
        $.ajax(
            {
                type:"post",
                dataType:"json",
                url:"deleteRestaurant",
                data:{
                    "restaurantId":restId
                },
                success:function (msg) {
                    alert("删除成功");
                    location.reload();
                }//end success

            }
        )//end ajax
    }


</script>
</html>
