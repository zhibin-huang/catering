<template>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th class="col-md-1">
                    <button class="btn btn-default btn-xs" data-toggle="modal" data-target="#addAddressModal">
                        <span class="glyphicon glyphicon-plus"></span>
                    </button>
                </th>
                <th class="col-md-2">地址名字</th>
                <th>地址</th>
                <th class="col-md-2"></th>
            </tr>
            </thead>
            <tbody>
            <template v-for="(address, index) in addresses">
                <address-list-item :address="address" :index="index" :username="username"
                                   @addressUpdated="updateAddresses"></address-list-item>
            </template>
            </tbody>
        </table>

        <div class="modal fade" id="addAddressModal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">添加地址</h4>
                    </div>
                    <div class="modal-body">
                        <form @submit.prevent="">
                            <div class="form-group">
                                <label>新地址</label>
                                <textarea type="text" name="username" class="form-control" maxlength="255" rows="4"
                                          v-model="newAddress"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button id="newAddressModalButton" type="button" class="btn btn-primary" data-dismiss="modal" @click="addAddress">
                            添加一个新地址
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import AddressListItem from "./AddressListItem.vue";

    export default {
        components: {AddressListItem},
        name: 'address-list',
        props: ['username'],
        data: function () {
            return {
                addresses: [],
                newAddress: ''
            }
        },
        created: function () {
            this.updateAddresses();
        },
        methods: {
            updateAddresses: function () {
                let self = this;
                $.get('/user/profile/address', function (data) {
                    self.addresses = data;
                });
            },
            addAddress: function () {
                let self = this;
                $.ajax({
                    url: '/user/profile/address',
                    type: 'POST',
                    data: {
                        address: this.newAddress
                    },
                    success: function (data) {
                        if (data === true) {
                            self.newAddress = '';
                            self.updateAddresses();
                        }
                    }
                });
            }
        }
    }
</script>