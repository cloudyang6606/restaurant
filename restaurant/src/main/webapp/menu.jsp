<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <title>菜单</title>
    <meta charset="UTF-8">
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div>
    <h1>${restaurantName}菜单管理</h1>
    <form id="form" method="post">
        <table border="1">
            <thead>
            <tr>
                <td>编号</td>
                <td>名称</td>
                <td>单价/元</td>
                <td>折扣/%</td>
                <td>类型</td>
                <td>描述</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="menu" items="${requestScope.menuList}">
                <tr>
                    <td>${menu.foodId}</td>
                    <td>${menu.name}</td>
                    <td>${menu.price}</td>
                    <td>${menu.discount}</td>
                    <td>${menu.type}</td>
                    <td>${menu.description}</td>
                    <td>
                        <a href="deleteFood?foodId=${menu.foodId}">删除</a>
                        <!--<button type="button" onclick="">修改</button>
                        <button type="button" onclick="deleteFood()">删除</button> -->
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td><input type="number" name="foodId" /></td>
                <td><input type="text" name="name"/></td>
                <td><input type="number" name="price"/></td>
                <td><input type="number" name="discount"/></td>
                <td><input type="text" name="type"/></td>
                <td><input type="text" name="description"/></td>
                <button type="button" onclick="addFood()">提交</button>

            </tr>
            </tbody>
        </table>
    </form>
</div>
<script>
    function addFood(){
        $.ajax({
            type:"post",
            datatype:"json",
            url:"addFood",
            data:$("#form").serialize(),
            success:function (msg) {
                if(msg.success ==true){
                    location.reload();
                } else{
                    alert(msg.message);

                }
            },
            error:function (){
                alert("添加失败");
            }
        })

    }

</script>

</body>
</html>
