<template>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="media">
                <div class="media-left media-middle">
                    <img class="media-object" :src="'/img/cover/' + food.coverFilename" height="40">
                </div>
                <div class="media-body">
                    <b class="media-heading">{{ food.foodTitle }}</b>
                    <br/>
                    <span class="text-muted">{{ food.author }}</span>
                </div>
            </div>
        </div>
        <div class="panel-body">
            <h4>{{ comment.title }}

                <small>{{ new Date(comment.commentTime).toLocaleDateString() }}</small>
            </h4>
            <p class="comment-content">{{ comment.content }}</p>
            <div>
                <button type="button" class="btn btn-default btn-sm" data-toggle="modal"
                        :data-target="'#' + commentModalId" @click="$emit('updateComment', comment)">Edit
                </button>
                <button type="button" class="btn btn-link btn-sm" @click="removeComment">Remove</button>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'comment-panel',
        props: ['commentModalId', 'comment'],
        data: function () {
            return {
                food: {coverFilename: '51frZKhRiIL._SX330_BO1,204,203,200_.jpg'}
            }
        },
        created: function () {
            let self = this;
            $.get("/food/" + this.comment.foodByFoodId.foodId, function (data) {
                self.food = data;
            });
        },
        methods: {
            removeComment: function () {
                $.ajax({
                    url: '/user/comment/' + this.comment.foodByFoodId.foodId,
                    type: 'DELETE',
                    success: function (data) {
                        if (data === true) {
                            window.location.reload();
                        }
                    }
                });
            }
        }
    }
</script>

<style>
    .comment-content {
        max-height: 300px;
        overflow-y: scroll;
    }
</style>