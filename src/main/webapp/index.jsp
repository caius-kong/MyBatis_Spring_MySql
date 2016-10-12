<%--
  Created by IntelliJ IDEA.
  User: kongyunhui
  Date: 16/9/14
  Time: 上午10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        function sendAjax() {
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/user/saveUser2.do',
                data: {
                    name: $("#name").val(),
                    sex: $("#sex").val()
                },
                success: function (data) {
                    alert(data.msg);
                }
            });
        }
    </script>
</head>
<body>
<ul>
    <li><a href="/user/getUser1.do?id=1">id=1的用户详细1(ModelView)</a></li>
    <li><a href="/user/getUser2.do?id=1">id=1的用户详细2(Model)</a></li>
    <li><a href="/user/getUser3.do?id=1">id=1的用户详细3(HttpServletRequest)</a></li>
    <li><a href="/user/getUsers.do">所有用户详细</a></li>
    <li><a href="/user/getUserByName.do?name=k">模糊查询"昀"用户</a></li>
    <li><a href="/user/getUserInfo.do?userInfoCustom.id=1&userInfoCustom.emailName=1032316751@qq.com">用户信息</a></li>
    <li><a href="/user/getUserInfo2.do">用户信息2</a></li>
    <li><a href="/user/getUserInfo3.do">用户信息3</a></li>
    <li><a href="/user/getEmailInfo.do">邮箱信息(延迟加载用户信息)</a></li>
    <li><a href="/user/getUsersByIds.do">查询ids指定的用户信息</a></li>
    <li>
        添加用户:
        <form action="/user/saveUser.do" method="post" enctype="multipart/form-data">
            <input type="text" name="name" id="name">
            <input type="text" name="sex" id="sex">
            <input type="text" name="birthday">
            <input type="file" name="user_pic">
            <input type="submit" value="提交">
            <input type="button" onclick="sendAjax();" value="ajax的方式提交">
        </form>
    </li>
</ul>

<div>
    <p>RESTful api demo</p>
    <button id="btnGet">获取用户</button>
    <button id="btnDelete">删除用户</button>
    <button id="btnInsert">新增用户</button>
    <button id="btnUpdate">更新用户</button>
    <button id="btnList">查询用户列表</button>
</div>

<script type="text/javascript">
    // 在公共的js中
    $().ready(function () {
        /**
         * 设置未来(全局)的AJAX请求默认选项
         * 主要设置了AJAX请求遇到Session过期的情况
         */
        $.ajaxSetup({
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if (XMLHttpRequest.status == 403) {
                    alert('您没有权限访问此资源或进行此操作');
                    return false;
                }
            },
            complete: function (XMLHttpRequest, textStatus) {
                var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头,sessionstatus，
                if (sessionstatus == 'timeout') {
                    //如果超时就处理 ，指定要跳转的页面
                    var top = getTopWinow(); //获取当前页面的顶层窗口对象
                    alert('登录超时, 请重新登录.');
                    top.location.href = "${pageContext.request.contextPath}/user/login"; //跳转到登陆页面
                }
            }
        });
        /**
         * 在页面中任何嵌套层次的窗口中获取顶层窗口
         * @return 当前页面的顶层窗口对象
         */
        function getTopWinow() {
            var p = window;
            while (p != p.parent) {
                p = p.parent;
            }
            return p;
        }
    });

    (function (window, $) {
        var crudUser = {
            url: '',
            init: function () {
                crudUser.url = '${pageContext.request.contextPath}/user';
                // 绑定点击事件,触发对应func
                $('#btnGet').click(crudUser.getUser);
                $('#btnDelete').click(crudUser.deleteUser);
                $('#btnInsert').click(crudUser.insertUser);
                $('#btnUpdate').click(crudUser.updateUser);
                $('#btnList').click(crudUser.listUser);
            },
            getUser: function () {
                $.ajax({
                    type: 'GET',
                    url: crudUser.url + '/user/1',
                    success: function (data) {

                    }
                });
            },
            deleteUser: function () {
                $.ajax({
                    type: 'DELETE',
                    url: crudUser.url + '/user/5',
                    success: function (data) {

                    }
                });
            },
            insertUser: function () {
                $.ajax({
                    type: 'POST',
                    url: crudUser.url + '/user',
                    data: {
                        name: 'k1',
                        sex: '男',
                        birthday: '1992-07-20'
                    },
                    success: function (data) {

                    }
                });
            },
            updateUser: function () {
                $.ajax({
                    type: 'POST', // 注意在传参数时，加：_method:'PUT'　将对应后台的PUT请求方法
                    url: crudUser.url + '/user',
                    data: {
                        _method: 'PUT',
                        id: '1',
                        sex: '女'
                    },
                    success: function (data) {

                    }
                });
            },
            listUser: function () {
                $.ajax({
                    type: 'POST', // 注意在传参数时，加：_method:'PATCH'　将对应后台的PATCH请求方法
                    url: crudUser.url + '/user',
                    data: {
                        _method: 'PATCH',
                        name: 'k'
                    },
                    success: function (data) {

                    }
                });
            }
        };
        window.crudUser = (window.crudUser) ? window.crudUser : crudUser;
        $(function () {
            crudUser.init();
        });
    })(window, jQuery);
</script>
</body>
</html>
