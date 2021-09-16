<%--
  Created by IntelliJ IDEA.
  User: zgp
  Date: 2021/9/11
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>TikTok</title>
    <style>
        body{
            padding: 0;
            margin: 0;
            font-family: "Microsoft YaHei", "Segoe UI", "Lucida Grande", Helvetica, Arial, sans-serif;
            font-size: 14px;
            color: #333;
        }
        .container{
            width: 590px;
            margin: 0 auto;
            padding-top: 100px;
            position: relative;
        }
        .container:after{
            content: '';
            display: block;
            clear:both;
        }
        .container>div{
            float: left;
        }
        .container .logo{
            width: 100%;
            height: 120px;
            margin: 0 auto 10px;
            background-position: center;
            background-repeat: no-repeat;
            background-size: auto 120px;
        }
        .container .input{
            position: relative;
            width: 500px;
            height: 44px;
            border-top: #ccc solid 1px;
            border-right: none;
            border-bottom: #ccc solid 1px;
            border-left: #ccc solid 1px;
            border-radius: 4px 0 0 4px;
        }
        .container .input>input{
            outline: none;
            border: none;
            padding: 0 80px 0 10px;
            margin: 0;
            height: 44px;
            width: 410px;
            color: #333;
            font-size: 16px;
            border-radius: 4px 0 0 4px;
        }
        .container .input>input::-webkit-input-placeholder{
            color:#999;
        }
        .container .input .picker{
            width: 40px;
            padding-right: 30px;
            position: absolute;
            top: 0;
            right: 0;
            height: 100%;
            line-height: 44px;
            cursor: pointer;
            color: #999;
            font-size: 12px;
            text-align: right;
            /*background-image: url("../system/img/down.png");*/
            background-position: 50px center;
            background-repeat: no-repeat;
            user-select: none;
        }
        .container .input .picker-list{
            list-style: none;
            padding: 5px 0;
            width: 100px;
            position: absolute;
            right: 0;
            top: 50px;
            margin: 0;
            line-height: 26px;
            font-size: 12px;
            border-radius: 2px;
            box-shadow: 0 1px 5px rgba(0,0,0,.2);
            background-color: #fff;
            display: none;
        }
        .container .input .picker-list>li{
            padding-left: 36px;
            background-position: 10px center;
            background-repeat: no-repeat;
            background-size: 16px auto;
        }
        .container .input .picker-list>li:hover{
            background-color: #ebf1f5;
            cursor: pointer;
        }
        .container .input .hot-list{
            padding: 10px 0;
            width: 100%;
            position: absolute;
            left: 0;
            top: 50px;
            margin: 0;
            line-height: 32px;
            font-size: 14px;
            border-radius: 2px;
            box-shadow: 0 1px 5px rgba(0,0,0,.2);
            background-color: #fff;
            display: none;
        }
        .container .input .hot-list>a{
            display: block;
            color: #333;
            text-decoration: none;
            padding: 0 10px;
            overflow: hidden;
        }
        .container .input .hot-list>a:hover{
            background-color: #f3f3f3;
        }
        .container .input .hot-list>a>div{
            float: left;
        }
        .container .input .hot-list>a>div.number{
            text-align: center;
            width: 40px;
        }
        .container .search{
            width: 89px;
            height: 46px;
            background-color: #08f;
            background-image: url("http://139.199.213.175:8888/down/AT8yMJjObU12");
            background-position: center;
            background-repeat: no-repeat;
            border-radius: 0 4px 4px 0;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">

    <div class="input">
        <input type="text" placeholder="请输入要解析的链接!" autocomplete="off" id="search-input">
    </div>
    <div class="search" id="subSearch"></div>
    <br/><br/><br/>

    <div>
        <h2>当前解析结果如下!</h2>
        <textarea cols="80px" rows="20" id="textareaValue"></textarea>
    </div>

</div>
<script src="https://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script>
    //点击解析
    $("#subSearch").click(function () {
        //获取输入解析链接
        var val = $("#search-input").val();
        //判断链接是否为空
        if (val !="" && val !=null){
            var url="/TikTokLinkParsingTool/linkparsing/getLinkParsing";
            $.ajax(url, {
                data: {
                    "url": val,
                },
                //服务器返回json格式数据
                dataType: 'json',
                //HTTP请求类型
                type: 'post',
                //超时时间设置为10秒
                timeout: 10000,
                //成功响应
                success: function (response) {
                    //获取成功状态码
                    var result_code=response.result_code;
                    if (result_code=="0000"){
                        var result_data=response.result_data;
                        var nowTime=response.nowTime;
                        $("#textareaValue").val("解析时间为:"+nowTime+"    "+"url为: "+result_data);
                    }else if(result_code="9999"){
                        var result_msg=response.result_msg;
                        var nowTime=response.nowTime;
                        $("#textareaValue").val("解析时间为:"+nowTime+"    "+"url为: "+result_msg);
                    }
                },
                //失败响应
                error: function () {
                    alert("亲,接口或网络异常啦!")
                }
            });
        }else {
            alert("亲，请输入你的链接信息！")
        }
    });
</script>
</body>
</html>
