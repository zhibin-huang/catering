<template>
    <div>
        <h2>个人信息</h2>
        <hr>
        <div class="row">
            <form class="col-md-8">
                <div class="form-group">
                    <label class="control-label" for="bio">自我介绍</label>
                    <textarea name="bio" id="bio" class="form-control" placeholder="分享你的生活"
                              rows="4" v-model="bio"></textarea>
                </div>
                <div class="form-group">
                    <label class="control-label " for="location">位置</label>
                    <input type="email" name="location" class="form-control" id="location" placeholder="位置"
                           v-model="location">
                    <span class="help-block"></span>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-default" @click="updateProfile">更新你的个人信息</button>
                </div>
            </form>
            <div class="col-md-4">
                <a class="thumbnail">
                    <img :src="'/image/avatar/' + avatarFilename">
                </a>
                <button class="btn btn-default" @click="triggerAvatarInputClick">上传新头像</button>

                <form action="/image/avatar" method="post" enctype="multipart/form-data">
                    <input type="file" name="file" id="avatarInput" style="display: none;" @change="uploadPicture"/>
                </form>
            </div>
        </div>
        <h2>Subscription</h2>
        <hr>
        <div class="row">
            <form class="col-md-8">
                <div class="form-group">
                    <label>
                        <input type="checkbox" v-model="newsletters"> Subscript to Naïve Newsletters
                    </label>
                </div>
                <div class="form-group">
                    <label>
                        <input type="checkbox" v-model="autoRecharge">
                        Enable auto-recharge (recharge $100 when your credits is below $20)
                    </label>
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-default" @click="updateSubscription">Update Subscription
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'profile-form',
        props: ['profile'],
        data: function () {
            return {
                avatarFilename: 'default.jpg',
                bio: '',
                location: '',
                newsletters: false,
                autoRecharge: false,
                files: {}
            }
        },
        watch: {
            profile: function (newProfile) {
                this.avatarFilename = newProfile.avatarFilename;
                this.bio = newProfile.bio;
                this.location = newProfile.location;
                this.newsletters = newProfile.newsletters;
                this.autoRecharge = newProfile.autoRecharge;
            }
        },
        methods: {
            updateProfile: function () {
                $.ajax({
                    url: '/user/profile/profile',
                    type: 'PUT',
                    data: {
                        bio: this.bio,
                        location: this.location
                    },
                    success: function (data) {
                        if (data === true) {
                            window.location.reload()
                        }
                    }
                })
            },
            updateSubscription: function () {
                $.ajax({
                    url: '/user/profile/subscription',
                    type: 'PUT',
                    data: {
                        newsletters: this.newsletters,
                        autoRecharge: this.autoRecharge
                    },
                    success: function (data) {
                        if (data === true) {
                            window.location.reload()
                        }
                    }
                })
            },
            triggerAvatarInputClick: function () {
                $('#avatarInput').click();
            },
            uploadPicture: function (event) {
                let data = new FormData();
                data.append('file', event.target.files[0])

                $.ajax({
                    url: "/image/avatar",
                    type: "POST",
                    data: data,
                    contentType: false,
                    cache: false,
                    processData: false,
                    success: function (data) {
                        if (data === true) {
                            window.location.reload()
                        }
                    }
                });
            }
        }
    }
</script>