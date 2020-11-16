
import Vue from 'vue';
import Space from './space/Space.vue';

let app = new Vue({
    el: "#app",
    template: '<Space/>',
    components: {
        Space
    }
});
