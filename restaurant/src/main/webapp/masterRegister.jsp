<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>注册</title>
    <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div>
    <form id="form">
        编号:<input type="number" name="masterId" required/><br/>

        姓名:<input type="index" name="masterName" required/><br/>

        密码：<input type="password" name="masterPwd" required/><br/>

        确认密码:<input type="password" required><br/>

        <!--餐厅编号:<input type="number" name="restaurantId" required/><br/>

        餐厅名称:<input type="text" name="restaurantName" required/><br/>

        餐厅地址:<input type="text" name="address" required/><br/>

        描述:<input type="text" name="description" required/><br/>-->

        <button type="button" onclick="addMaster()">注册</button>

    </form>
</div>

</body>
<script>
    function addMaster() {
        $.ajax({
            type:"post",
            datatype:"json",
            url:"addRestaurant",
            data:$("#form").serialize(),
            success:function (msg) {
                if(msg.success ==true){
                    location.href="login.html";
                } else{
                    alert(msg.message);
                }
            },
            error:function (){
                alert("注册失败");
            }
        })
    }
</script>
</html>
