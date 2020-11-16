<template>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-push-2">
                <h1>现在就注册<br>
                    <small>小众点评</small>
                </h1>
                <hr>
                <div class="row">
                    <div class="col-md-8">
                        <form>
                            <div class="form-group">
                                <label for="username">用户名</label>
                                <input type="text" class="form-control" id="username" v-model="username">
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="text" class="form-control" id="email" v-model="email">
                            </div>
                            <div class="form-group">
                                <label for="password">密码</label>
                                <input type="password" class="form-control" id="password" v-model="password">
                            </div>
                            <hr>
                            <p>By clicking on "Create an account" below, you are agreeing to the <a
                                    data-toggle="tooltip" data-placement="top"
                                    title="Not available yet">Terms of Service</a>
                                and the <a data-toggle="tooltip" data-placement="top" title="Not available, either">Privacy Policy</a>.
                            </p>
                            <hr>
                            <div class="form-group">
                                <button type="button" @click="submitSignupForm" class="btn btn-success">点击注册</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'sign-up-form',
        data: function () {
            return {
                username: '',
                email: '',
                password: ''
            }
        },
        created: function () {
            $(function () {
                $('[data-toggle="tooltip"]').tooltip()
            })
        },
        methods: {
            submitSignupForm: function () {
                $.ajax({
                    url: '/user/account',
                    type: 'POST',
                    data: {
                        username: this.username,
                        email: this.email,
                        password: this.password
                    },
                    success: function (data) {
                        if (data === true) {
                            alert("Successfully singed up! Try to log in with your new account.")
                            window.location.href = '/login'
                        }
                    }
                })
            }
        }
    }
</script>