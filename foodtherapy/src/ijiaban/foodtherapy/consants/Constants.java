package ijiaban.foodtherapy.consants;

public class Constants {
  public static final String BAR_URL="http://api.yi18.net/food/bar";
  public static final String MENU_URL="http://api.yi18.net/food/menu";
//  public static String NEWS_URL="http://api.yi18.net/news/list?page=1&limit=10&type=id&id=5";
  public static String NEWS_COMMON_URL="http://api.yi18.net/news/list?page=";
  /**
   *   {"name":"社会热点","id":6},
         {"name":"食品新闻","id":5},
         {"name":"药品新闻","id":4},
         {"name":"生活贴士","id":3},
         {"name":"医药新闻","id":2},
         {"name":"企业要闻","id":1}
   */
  public static final String NEWS_ID="&id=";   
  public static String NEWS_TYPE="&type=";/**id  时间  ，count点击量*/
  public static final String NEWS_LIMIT="&limit=";
  public static final String DETAIL_COOK="http://api.yi18.net/cook/show?id=";
  public static final String DETAIL_FOOD="http://api.yi18.net/food/show?id=";
  public static final String DETAIL_NEWS="http://api.yi18.net/news/show?id=";
}
