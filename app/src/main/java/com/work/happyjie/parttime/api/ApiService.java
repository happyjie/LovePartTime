package com.work.happyjie.parttime.api;

import com.work.happyjie.parttime.http.request.LoginRequestModel;
import com.work.happyjie.parttime.http.response.GankIoDataResult;
import com.work.happyjie.parttime.http.response.GetFinanceInfoResponse;
import com.work.happyjie.parttime.http.response.GetHomeDataResponse;
import com.work.happyjie.parttime.http.response.GetIncomDetailResponse;
import com.work.happyjie.parttime.http.response.GetStudyInfoResponse;
import com.work.happyjie.parttime.http.response.GetTaskListResponse;
import com.work.happyjie.parttime.http.response.JokeCommentResult;
import com.work.happyjie.parttime.http.response.JokeContentTypeResult;
import com.work.happyjie.parttime.http.response.JokeListResult;
import com.work.happyjie.parttime.http.response.LoginResponse;
import com.work.happyjie.parttime.http.response.NewsDataResult;
import com.work.happyjie.parttime.http.HttpUtils;
import com.work.happyjie.parttime.http.response.base.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by llj on 2017/12/13.
 */

public interface ApiService {

    ApiService gankApiService = HttpUtils.getInstance().getGankApiService();
//    ApiService musicApiService = HttpUtils.getInstance().getMusicApiService();
//    ApiService doubanApiService = HttpUtils.getInstance().getDoubanApiService();
    ApiService newsApiService = HttpUtils.getInstance().getNewsApi();
    ApiService jokeApiService = HttpUtils.getInstance().getJokeApi();
    ApiService partTimeService = HttpUtils.getInstance().getPartTimeApi();

    /**
     * 登陆
     * @param
     * @return
     */
//    @POST("SpringMvc/LoginController/login_post.json")
//    Observable<LoginResponse> login(@Body LoginRequestModel request);


    @POST("SpringMvc/LoginController/login_post.json")
    Observable<LoginResponse> login(@Query("username") String userName, @Query("password") String password,
                                    @Query("versionName") String versionName);

    /**
     * 查询首页数据
     * @param userName
     * @return
     */
    @POST("SpringMvc/IncomeController/income_post.json")
    Observable<GetHomeDataResponse> getHomeData(@Query("username") String userName);

    /**
     * 查询收入明细
     */
    @POST("SpringMvc/IncomeController/queryIncomeDetail.json")
    Observable<GetIncomDetailResponse> getIncomingDetail(@Query("username") String userName, @Query("year") int year,
                                                         @Query("month") int month, @Query("currPage") int curPage,
                                                         @Query("pageSize") int pageSize);

    /**
     * 查询财务明细
     */
    @POST("SpringMvc/IncomeController/queryfinanceDetail.json")
    Observable<GetFinanceInfoResponse> getFinanceInfo(@Query("username") String userName,/* @Query("year") int year,
                                                         @Query("month") int month, */@Query("currPage") int curPage,
                                                         @Query("pageSize") int pageSize);


    /**
     * 提现
     * @param userName
     * @param drawcashnum
     * @return
     */
    @POST("SpringMvc/IncomeController/drawcash.json")
    Observable<BaseResponse> drawCash(@Query("username") String userName, @Query("drawcashnum") float drawcashnum);


    /**
     * 自动刷单
     * @param userName
     * @return
     */
    @POST("SpringMvc/TaskController/autoTask.json")
    Observable<BaseResponse> autoTask(@Query("username") String userName, @Query("param1") long param1,
                                      @Query("param2") String param2);

    /**
     * 新手教学
     * @param userName
     * @return
     */
    @POST("SpringMvc/LoginController/teach.json")
    Observable<GetStudyInfoResponse> getStudyInfo(@Query("username") String userName);

    /**
     * 任务列表
     * @param userName
     * @return
     */
    @POST("SpringMvc/TaskController/queryTask.json")
    Observable<GetTaskListResponse> getTaskList(@Query("username") String userName, @Query("finishstate") int finishstate,
                                                @Query("currPage") int currPage, @Query("pageSize") int pageSize);


    /**
     * 分享任务成功后统计数据
     * @param userName
     * @param taskId
     * @return
     */
    @POST("SpringMvc/TaskController/transmit.json")
    Observable<BaseResponse> shareTaskSuccess(@Query("username") String userName, @Query("taskid") String taskId,
                                              @Query("param1") long param1, @Query("param2") String param2);


    /**
     * 任务领取
     * @param userName
     * @param taskId
     * @return
     */
    @POST("SpringMvc/TaskController/getTask.json")
    Observable<BaseResponse> receiveTask(@Query("username") String userName, @Query("taskid") String taskId);

    /**
     * 分类数据: http://gank.io/api/data/数据类型/每页数据量/第几页
     * type 分类类型：福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
     * per_page 每页数据条数
     * page 第几页 从1开始
     * eg: http://gank.io/api/data/Android/10/1
     */
    @GET("data/{type}/{pre_page}/{page}")
    Observable<GankIoDataResult> getGankIoData(@Path("type") String type, @Path("pre_page") int pre_page, @Path("page") int page);


    /**
     * 获取今日头条的新闻数据
     * @return
     */
    @GET("feed/v54/")
    Observable<NewsDataResult> getNewsData(@Query("category") String category, @Query("refer") int refer, @Query("count") int count,
                                           @Query("min_behot_time") long min_behot_time,
                                           @Query("last_refresh_sub_entrance_interval") long last_refresh_sub_entrance_interval,
                                           @Query("loc_mode") int loc_mode, @Query("loc_time") long loc_time,
                                           @Query("latitude") double latitude, @Query("longitude") double longitude,
                                           @Query("city") String city);


    /**
     * 获取内涵段子分类数据
     * @param essence
     * @param ac
     * @param app_name
     * @param version_code
     * @param version_name
     * @param device_platform
     * @param device_type
     * @param device_brand
     * @param os_api
     * @param os_version
     * @return
     */
    @GET("neihan/service/tabs/")
    Observable<JokeContentTypeResult> getJokeContentType(@Query("essence") int essence, @Query("ac") String ac, @Query("channel") String channel,
                                                         @Query("app_name") String app_name, @Query("version_code") int version_code,
                                                         @Query("version_name") String version_name, @Query("device_platform") String device_platform,
                                                         @Query("device_type") String device_type, @Query("device_brand") String device_brand,
                                                         @Query("os_api") int os_api, @Query("os_version") String os_version);


    /**
     * 获取内涵段子列表
     * @param path
     * @param essence
     * @param ac
     * @param channel
     * @param app_name
     * @param version_code
     * @param version_name
     * @param device_platform
     * @param device_type
     * @param device_brand
     * @param os_api
     * @param os_version
     * @param webp
     * @param content_type
     * @param message_cursor
     * @param am_longitude
     * @param am_latitude
     * @param am_city
     * @param am_loc_time
     * @param count
     * @param min_time
     * @param double_col_mode
     * @return
     */
    @GET("{path}")
    Observable<JokeListResult> getJokeList(@Path("path") String path, @Query("essence") int essence, @Query("ac") String ac,
                                           @Query("channel") String channel, @Query("app_name") String app_name, @Query("version_code") int version_code,
                                           @Query("version_name") String version_name, @Query("device_platform") String device_platform,
                                           @Query("device_type") String device_type, @Query("device_brand") String device_brand,
                                           @Query("os_api") int os_api, @Query("os_version") String os_version,
                                           @Query("webp") int webp, @Query("content_type") int content_type, @Query("message_cursor") int message_cursor,
                                           @Query("am_longitude") String am_longitude, @Query("am_latitude") String am_latitude,
                                           @Query("am_city") String am_city, @Query("am_loc_time") long am_loc_time,
                                           @Query("count") int count, @Query("min_time") long min_time,
                                           @Query("double_col_mode") int double_col_mode);


    @GET("neihan/comments/")
    Observable<JokeCommentResult> getJokeComment(@Query("essence") int essence, @Query("ac") String ac, @Query("channel") String channel,
                                                 @Query("app_name") String app_name, @Query("version_code") int version_code,
                                                 @Query("version_name") String version_name, @Query("device_platform") String device_platform,
                                                 @Query("device_type") String device_type, @Query("device_brand") String device_brand,
                                                 @Query("os_api") int os_api, @Query("os_version") String os_version,
                                                 @Query("group_id") long group_id, @Query("item_id") long item_id, @Query("count") int count,
                                                 @Query("offset") int offset, @Query("enable_image_comment") int enable_image_comment);
}
