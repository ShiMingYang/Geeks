package com.example.dell.small_geeknews.geeknews.geekbean;

import java.util.List;

/**
 * Created by Dell on 2019/4/17.
 */

public class DayNewsBean {

    /**
     * date : 20190417
     * stories : [{"title":"「超级视力」来了，经过改造的小鼠可裸眼看见红外线","ga_prefix":"041716","images":["https://pic1.zhimg.com/v2-9d22c120d2206f4c31f9525cab5027f8.jpg"],"multipic":true,"type":0,"id":9710284},{"title":"用游戏「实地」图解：巴黎圣母院为什么会着火？","ga_prefix":"041709","images":["https://pic2.zhimg.com/v2-9cdc4b2a57325d83f171a5675485e655.jpg"],"multipic":true,"type":0,"id":9710280},{"images":["https://pic3.zhimg.com/v2-927b72fe1ea8562b14544bc222069586.jpg"],"type":0,"id":9710298,"ga_prefix":"041708","title":"被正面表达的诉求，才有被解决的可能"},{"title":"三星 Galaxy Fold：它是一台手机，但屏幕告诉你它不是","ga_prefix":"041707","images":["https://pic3.zhimg.com/v2-3cd88bc0d9d888ffd947b01b38b6544a.jpg"],"multipic":true,"type":0,"id":9710289},{"images":["https://pic3.zhimg.com/v2-48280d6d028fcca9fff19e296d204ece.jpg"],"type":0,"id":9710274,"ga_prefix":"041706","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic1.zhimg.com/v2-e61227b2e6f420f6c62a71f089db8100.jpg","type":0,"id":9710289,"ga_prefix":"041707","title":"三星 Galaxy Fold：它是一台手机，但屏幕告诉你它不是"},{"image":"https://pic2.zhimg.com/v2-4b16fd1c9c22d5bc115d75100bcc536d.jpg","type":0,"id":9710280,"ga_prefix":"041709","title":"用游戏「实地」图解：巴黎圣母院为什么会着火？"},{"image":"https://pic3.zhimg.com/v2-26b7a11ab1f5ef8b4aadaf773069f48e.jpg","type":0,"id":9710258,"ga_prefix":"041607","title":"一个巨大的汽车行业潜规则，就这么不经意间捅了出来"},{"image":"https://pic1.zhimg.com/v2-18c6d20db38a2d8b0efc746f09803830.jpg","type":0,"id":9710248,"ga_prefix":"041521","title":"「权游」最终季第 1 集：最好最好的重逢，久违了"},{"image":"https://pic2.zhimg.com/v2-32ecfc49df86c8d9285e16794aa31e91.jpg","type":0,"id":9710210,"ga_prefix":"041420","title":"「支付宝到账 XXX 元」，真的有催眠效果吗？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

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

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class TopStoriesBean {

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
