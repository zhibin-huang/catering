<template>
    <div>
        <div class="container">
            <div class="row">
                <template v-for="col in cols">
                    <div class="col-md-3">
                        <template v-for="shop in col">
                            <shop-thumbnail :shop="shop" @clickShop="showModal"></shop-thumbnail>
                        </template>
                    </div>
                </template>
            </div>
        </div>

        <!-- shop modal associated with this shop table -->
        <shop-modal :loggedIn="loggedIn" :shop="selectedShop" @foodAdded="$emit('foodAdded')"></shop-modal>
    </div>
</template>

<script>
    import ShopThumbnail from './ShopThumbnail.vue'
    import ShopModal from "./ShopModal.vue";

    export default {
        name: 'shop-table',
        components: {
            ShopModal,
            ShopThumbnail
        },
        props: ['loggedIn', 'shops'],
        data: function () {
            return {
                selectedShop: {  // placeholder for shop modal when created
                    "shopId": 1,
                    "title": "XiaoShiTang",
                    "city": "Xuancheng",
                    "district": "Xuanzhouqu",
                    "landmark": "HFUT",
                    "address": "XunHua Road 301",
                    "starRate": "4",
                    "coverFilename": "Short description.",
                    "description": "haochi",
                    "foodTag": "徽菜"
                }
            }
        },
        computed: {
            cols: function () {
                let cols = [[], [], [], []];
                for (let i = 0; i < this.shops.length; ++i) {
                    cols[i % 4].push(this.shops[i]);
                }

                return cols;
            }
        },
        methods: {
            showModal: function (selectedShop) {
                this.selectedShop = selectedShop;
            }
        }
    }
</script>