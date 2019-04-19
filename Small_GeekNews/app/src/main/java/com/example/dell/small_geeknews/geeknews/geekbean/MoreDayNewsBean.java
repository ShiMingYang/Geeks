package com.example.dell.small_geeknews.geeknews.geekbean;

import java.util.List;

/**
 * Created by Dell on 2019/4/18.
 */

public class MoreDayNewsBean {

    /**
     * date : 20190417
     * stories : [{"images":["https://pic3.zhimg.com/v2-af1691064b16e300cadeccfe1a2542c6.jpg"],"type":0,"id":9710376,"ga_prefix":"041722","title":"小事 · 不然真的没法过了"},{"images":["https://pic4.zhimg.com/v2-dfcf0541558587ac9af59bf954908c73.jpg"],"type":0,"id":9710335,"ga_prefix":"041719","title":"《刺客信条》真的对修复巴黎圣母院有帮助吗?"},{"title":"「超级视力」来了，经过改造的小鼠可裸眼看见红外线","ga_prefix":"041716","images":["https://pic1.zhimg.com/v2-9d22c120d2206f4c31f9525cab5027f8.jpg"],"multipic":true,"type":0,"id":9710284},{"title":"用游戏「实地」图解：巴黎圣母院为什么会着火？","ga_prefix":"041709","images":["https://pic2.zhimg.com/v2-9cdc4b2a57325d83f171a5675485e655.jpg"],"multipic":true,"type":0,"id":9710280},{"images":["https://pic3.zhimg.com/v2-927b72fe1ea8562b14544bc222069586.jpg"],"type":0,"id":9710298,"ga_prefix":"041708","title":"被正面表达的诉求，才有被解决的可能"},{"title":"三星 Galaxy Fold：它是一台手机，但屏幕告诉你它不是","ga_prefix":"041707","images":["https://pic3.zhimg.com/v2-3cd88bc0d9d888ffd947b01b38b6544a.jpg"],"multipic":true,"type":0,"id":9710289},{"images":["https://pic3.zhimg.com/v2-48280d6d028fcca9fff19e296d204ece.jpg"],"type":0,"id":9710274,"ga_prefix":"041706","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    @Override
    public String toString() {
        return "MoreDayNewsBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                '}';
    }


}
