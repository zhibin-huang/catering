<template>
    <div class="thumbnail" :id="shop.shopId">
        <img class="shop-cover" :src="'/image/avatar/' + shop.coverFilename"
             :alt="'cover of ' + shop.title" @click="clickShop" data-toggle="modal"
             data-target="#shop-modal">
        <div class="caption">
            <h3 class="shop-title" @click="clickShop" data-toggle="modal"
                data-target="#shop-modal">{{ shop.title }}
                <small>{{ shop.city }}{{ shop.district }}</small>
            </h3>
            <p>{{ shop.address}}</p>
            <span class="label label-info">{{ shop.starRate }}星</span>
          <span class="label label-info">{{ shop.foodTag }}</span>
          <span class="label label-info">{{ shop.landmark }}</span>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'shop-thumbnail',
        props: ['shop'],
        methods: {
            clickShop: function () {
                this.$emit("clickShop", this.shop);
            }
        },
        filters: {
            descriptionPreview: function (description) {
                return description.slice(0, 100);
            }
        }
    }
</script>