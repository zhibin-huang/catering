<template>
    <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" id="shop-modal">
        <div class="modal-dialog modal-lg" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <!-- info part -->
                    <br>
                    <div class="row">
                        <!-- shop cover -->
                        <div class="col-md-4 col-md-offset-1">
                            <img :src="'/image/avatar/' + shop.coverFilename" :alt="'cover of ' + shop.title"
                                 class="img-thumbnail">
                        </div>
                        <div class="col-md-6">
                            <!-- shop info -->
                            <h2>{{ shop.title }}
                                <small>{{ shop.foodTag }}</small>
                            </h2>
                            <br>

                            <div class="shop-address">
                                <p> {{shop.address}}</p>
                            </div>
                            <br>
                            <p>
                                <span class="label label-info">{{ shop.starRate }}星</span>
                                <span class="label label-info">{{ shop.city }}</span>
                                <span class="label label-info">{{ shop.district }}</span>
                                <span class="label label-info">{{ shop.landmark }}</span>
                            </p>
                          <div class="row mx-auto w-75">
                            <div class="col-12">
                              <table class="table table-hover table-success">
                                <thead class="thead-default">
                                <tr>
                                  <th>菜名</th>
                                  <th>价格</th>
                                  <th>数量</th>
                                  <th>封面</th>
                                  <th><transition name="fade">
                                    <p class="text-success" v-show="promptSuccess">
                                      {{ '' + quantity + '个菜品' }}  加进购物车!
                                    </p>
                                  </transition></th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="(food,index) in foods">
                                  <td>{{food.foodTitle}}</td>
                                  <td>{{food.price}}</td>
                                  <td>{{food.stock}}</td>
                                  <td> <img :src="'/image/avatar/' + food.coverFilename" :alt="'cover of ' + food.title"
                                             height="100" class="img-thumbnail">
                                  </td>
                                  <td>
                                    <div class="col-md-5 input-group">
                                    <span class="input-group-btn">
                                    <button class="btn btn-default" :id="'addButton'+food.foodId" @click="addToCart(food.foodId)"
                                            aria-label="Left Align" :disabled="!loggedIn">
                                        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                    </button>
                                     </span>
                                    </div>
                                  </td>
                                </tr>
                                </tbody>
                              </table>
                            </div>
                          </div>
                        </div>
                    </div>
                    <br>

                    <!-- comment part -->
                    <template v-if="comments.length > 0">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <h2>User comments</h2>
                            </div>
                        </div>
                    </template>
                    <template v-for="comment in comments">
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                <h3>{{ comment.title }}</h3>
                                <strong>{{ comment.userByUserId.username }}</strong>
                                at <strong>{{ new Date(comment.commentTime).toLocaleString() }}</strong>
                                <p>{{ comment.content }}</p>
                            </div>
                        </div>
                    </template>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'shop-modal',
        props: ['shop', 'loggedIn'],
        data: function () {
            return {
                comments: [],
                quantity: 1,
                foods:[],
                promptSuccess: false
            }
        },
        watch: {
            shop: function (newShop) {
                const self = this;
                $.get("/shop/comment/" + newShop.shopId, function (data) {
                    self.comments = data;
                });
                $.get("/food?shopId=" + newShop.shopId, function (data) {
                    self.foods = data;
                })
            }
        },
        methods: {
            addToCart: function ( foodId) {
                let self = this;
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

    .shop-description {
        max-height: 300px;
        overflow-y: scroll;
    }
</style>