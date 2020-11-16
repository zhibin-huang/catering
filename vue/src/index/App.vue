<template>
    <div id="app">
        <navbar activeTag="home" :loggedIn="loggedIn" @queryShop="reloadShops" ref="navbar"></navbar>
        <shop-table :loggedIn="loggedIn" :shops="shops" @foodAdded="updateCartSize"></shop-table>

        <div class="text-center">
            <button class="btn btn-default" v-show="!lastPage" @click="showMore">查看更多</button>
        </div>

        <page-footer></page-footer>
    </div>
</template>

<script>
    import Navbar from "../components/Navbar.vue";
    import ShopTable from "./ShopTable.vue";
    import PageFooter from "../components/PageFooter.vue";

    export default {
        components: {
            PageFooter,
            ShopTable,
            Navbar
        },
        data: function () {
            return {
                loggedIn: false,
                shops: [],
                lastPage: true,
                page: 0,
                totalPages: 0,
                q: ''
            }
        },
        created: function () {
            const self = this;
            $.get("/logged_in", function (data) {
                self.loggedIn = (data === true);
            });

            this.reloadShops();
        },
        methods: {
            updateCartSize: function () {
                this.$refs.navbar.updateCartSize();  // directly ask navbar to update cart size. ugly code tho
            },
            showMore: function () {
                if (this.lastPage) {
                    return;
                }

                let data;
                if (this.q === '') {
                    data = {
                        page: this.page + 1
                    }
                } else {
                    data = {
                        page: this.page + 1,
                        q: this.q
                    }
                }

                const self = this;
                $.get("/shop", data, function (data) {
                        self.shops.push.apply(self.shops, data.content)
                        self.lastPage = data.last;
                        self.page = data.number;
                        self.totalPages = data.totalPages;
                    }
                )
            },
            reloadShops: function (keyword) {
                const self = this;

                if (keyword === undefined) {
                      $.get("/shop", {page: 0}, function (data) {
                              self.shops = data.content;
                              self.lastPage = data.last;
                              self.page = data.number;
                              self.totalPages = data.totalPages;
                          }
                      )
                } else {
                    this.q = keyword;
                    $.get("/shop", {page: 0, q: keyword}, function (data) {
                            self.shops = data.content;
                            self.lastPage = data.last;
                            self.page = data.number;
                            self.totalPages = data.totalPages;
                        }
                    )
                }
            }
        }
    }
</script>

<style>
    body {
        padding-top: 70px;
        padding-bottom: 20px;
    }
</style>