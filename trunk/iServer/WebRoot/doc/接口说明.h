type:1和2，分别代表小康和财智
isIpad: 0和1.分别代表是否是ipad版（返回的图片大小不一）
参数中：%@为需要代入的变量参数

#define NEWS_MAG_ALL_URL @"http://118.145.16.102:8080/iServer/magazine/iphone/maglist?type=2"

#define NEWS_MAG_URL @"http://118.145.16.102:8080/iServer/magazine/iphone/class?type=2&isIpad=1"

//推荐文章：上端部分
#define NEWS_RECOMMEND_TOP_URL @"http://118.145.16.102:8080/iServer/article/iphone/recommend/top?type=2&isIpad=1&magazineId=%@"
//推荐文章：下端部分
#define NEWS_RECOMMEND_URL @"http://118.145.16.102:8080/iServer/article/iphone?type=2&isIpad=1&isRecommend=1&magazineId=%@"
#define NEWS_RECOMMEND_NEXT_URL @"http://118.145.16.102:8080/iServer/article/iphone?type=2&isIpad=1&isRecommend=1&magazineId=%@&newsId=%@"


#define NEWS_KIND_URL @"http://118.145.16.102:8080/iServer/section/iphone?type=2&isIpad=1&magazineId=%@&rp=100"
#define NEWS_KIND_DETAIL_TOP_URL @"http://118.145.16.102:8080/iServer/article/iphone/top?type=2&isIpad=1&cid=%@"
#define NEWS_KIND_DETAIL_URL @"http://118.145.16.102:8080/iServer/article/iphone?type=2&isIpad=1&cid=%@"
#define NEWS_KIND_DETAIL_NEXT_URL @"http://118.145.16.102:8080/iServer/article/iphone?type=2&isIpad=1&cid=%@&newsId=%@"
#define NEWS_KIND_DETAIL_CONTENT_URL @"http://118.145.16.102:8080/iServer/article/name/iphone?type=2&isIpad=1&nid=%@"

//用户登陆
#define MAG_USER_LOGIN_URL @"http://118.145.16.102:8080/iServer/user/iphone/checkLogin"
//注册
#define MAG_USER_REGISTER_URL @"http://118.145.16.102:8080/iServer/user/iphone/register"
#define DISSCUZ_MESSAGE_LIST_URL @"http://118.145.16.102:8080/iServer/comment/iphone/?count=%d&nid=%d&begin=%d"
#define DISSCUZ_MESSAGE_DETAIL_URL @"http://118.145.16.102:8080/iServer/comment/iphone/detail/?tid=%@"
//提交评论
#define DISSCUZ_SEND_MESSAGE_URL @"http://118.145.16.102:8080/iServer/comment/iphone/sendComment"
#define DISSCUZ_TYPE_LIST_URL @"http://118.145.16.102:8080/iServer/comment/iphone/comment/typelist"