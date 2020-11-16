<template>
    <li class="media">
        <div class="media-left">
            <img class="media-object" :src="'/image/avatar/' + item.foodByFoodId.coverFilename"
                 width="80">
        </div>
        <div class="media-body">
            <div class="row">
                <div class="col-md-8">
                    <h4 class="media-heading">{{ item.foodByFoodId.foodTitle }}<br/>
                        <small>{{ item.foodByFoodId.author }}</small>
                    </h4>
                    <p>
                        <span class="label label-info">{{ item.quantity }} {{ item.quantity === 1 ? 'copy' : 'copies'
                            }}</span>
                        <span class="label label-info">${{ item.originalUnitPrice / 100 }}</span>
                    </p>
                    <button class="btn btn-default btn-sm" @click="addToCart(item.foodByFoodId.foodId)">
                        Buy it again
                    </button>
                    <transition name="fade">
                        <p class="text-success" v-show="promptSuccess">1 food added to cart!</p>
                    </transition>
                </div>
                <div class="col-md-4">
                    <button class="btn btn-default  btn-block" data-toggle="modal"
                            :data-target="'#' + commentModalId"
                            @click="$emit('commentFood', item.foodByFoodId.foodId)">
                        写评论
                    </button>
                    <button class="btn btn-default btn-block" disabled="disabled">退款</button>
                </div>
            </div>
        </div>
    </li>
</template>

<script>
    export default {
        name: 'order-item-media-list-item',
        props: ['item', 'commentModalId'],
        data: function () {
            return {
                promptSuccess: false
            }
        },
        methods: {
            addToCart: function (foodId) {
                const self = this;
                $.post('/user/cart', {
                    foodId: foodId,
                    quantity: 1
                }, function (data) {
                    if (data === true) {
                        self.promptSuccess = true;
                        setTimeout(function () {
                            self.promptSuccess = false;
                        }, 2000);
                        self.$emit('foodAdded');
                    }
                })
            }
        }
    }
</script>

<style>
    .fade-enter-active, .fade-leave-active {
        transition: opacity .5s
    }

    .fade-enter, .fade-leave-active {
        opacity: 0
    }
</style>