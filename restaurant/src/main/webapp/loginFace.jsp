<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>demo01</title>

    <script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <style type="text/css">
        .line{

            position: absolute;

            top: 300px; left: -80px;

            z-index: 2;

            height: 15px; width: 300px;

            background: linear-gradient(#33ffff, #66cccc,#99cccc); opacity: 0.7 ;/* 标准的语法 */

            /*动画效果*/

            animation: myScan 3s infinite alternate;

            -webkit-animation: myScan 3s infinite alternate;

        }
        @keyframes  myScan{

            from { top:5px; }

            to { top: 300px; }

        }

        -webkit-@keyframes  myScan{

                    from { top:5px; }

                    to { top: 600px; }

                }
        /*.box{background-image: url('image/bg2.jpg');width: 100%;height: 640px;background-size:100%, 100%;}*/
        .video{width:250px;height:250px;margin:auto}

    </style>
</head>
<body>
<div class="box">
    <div class="right">
        <div class="video">

            <!-- <div class="line"></div> -->

            <video id="myVideo" src="" class="video" ></video>

        </div>
        <canvas id="myCanvas" width="600" height="400" hidden="hidden"></canvas>
        <script>
            var myVideo = document.getElementById('myVideo');
            navigator.mediaDevices.getUserMedia({
                video: true
            }).then(function (mediaStream) {
                myVideo.srcObject = mediaStream;
                myVideo.onloadedmetadata = function () {
                    /* myVideo.controls = "controls"; 不显示控件*/
                    myVideo.play();
                }
            });

            function detectFace(){
                var canvas = document.getElementById('myCanvas').getContext('2d');
                canvas.drawImage(myVideo, 0, 0);
                var imgSrc = document.getElementById("myCanvas").toDataURL(
                    "image/png").split("base64,")[1];
                $.ajax({
                    type: "POST",
                    url:"RegisterFace",
                    data:{
                        userName:"yang",
                        userPwd:"1234",
                        image:imgSrc
                    },
                    success:function(msg){

                        if(msg == "success"){
                            window.location="loginSuccess.jsp"
                        }
                        else{

                            return;
                        }
                    }
                })
            }
            setInterval("detectFace()","2000");  //每隔一秒执行一次函数截图


            //将图片Base64 转成文件
        </script>

        <div id="iframe">
        </div>
    </div>
</div>
</body>
</html>