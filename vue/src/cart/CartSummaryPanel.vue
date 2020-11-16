<template>
    <div>
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">确认订单</h3>
            </div>
            <div class="panel-body">
                <div class="form-group">
                    <label for="inputShipping">送往</label>
                    <textarea class="form-control" id="inputShipping" name="deliverTo" v-model="deliverTo" rows="4"
                              placeholder="Your address here"></textarea>
                    <a href="#" @click.prevent="" data-toggle="modal" data-target="#addressBookModal">选择地址</a>
                </div>
                <div :class="{'form-group': true, 'has-warning': !enoughCredit}">
                    <label>Total</label>
                    <p>${{ totalPrice / 100 }} <span class="text-success" v-show="discountEnable">* 85%</span></p>
                    <span class="help-block" v-show="!enoughCredit">余额不足.</span>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox" v-model="discountEnable"> 常客打折!
                        </label>
                    </div>
                </div>
                <button class="btn btn-default" @click="submitOrder">支付</button>
            </div>
        </div>

        <div class="modal fade" id="addressBookModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">我的地址</h4>
                    </div>
                    <div class="modal-body">
                        <ul class="list-group" v-if="addresses.length > 0">
                            <template v-for="address in addresses">
                                <a href="#" class="list-group-item" @click="pickAddress">{{ address }}</a>
                            </template>
                        </ul>
                        <div class="alert alert-warning" role="alert" v-else>暂无可用地址.</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'cart-summary-panel',
        props: ['totalPrice'],
        data: function () {
            return {
                deliverTo: "",
                discountEnable: false,
                enoughCredit: true,
                addresses: []
            }
        },
        created: function () {
            let self = this;
            $.get('/user/profile/address', function (data) {
                self.addresses = data;
            });
        },
        watch: {
            totalPrice: function (newTotalPrice) {
                let self = this;
                $.get('/user/account/credit', function (data) {
                    if (typeof data === 'number') {
                        self.enoughCredit = newTotalPrice <= data;
                    }
                })
            }
        },
        methods: {
            submitOrder: function () {
                const self = this;
                $.post('/user/order', {
                    deliverTo: this.deliverTo
                }, function (data) {
                    if (data === true) {
                        window.location.href = '/space.html';
                    }
                })
            },
            pickAddress: function (event) {
                this.deliverTo = $(event.target).text();
                $('#addressBookModal').modal('hide');
            }
        }
    }
</script>