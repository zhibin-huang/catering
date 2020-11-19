package me.catering.demo.domain;


public class JacksonView {
    public interface CommentView {}

    public interface OrderView {}

    public interface UserView{}

    public interface ShopView{}

    public interface FoodView{}

    public interface Comment_FoodView extends CommentView,ShopView{}

    public interface Order_FoodView extends OrderView,FoodView{}
}
