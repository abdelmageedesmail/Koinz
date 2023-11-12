#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_abdelmageed_flickersimages_data_repository_ImageRepositoryImpl_getApiKey(JNIEnv *env,
                                                                                  jobject thiz) {
    std::string apiKey = "6d39390b9067db8926e05a566f995003";
    return env->NewStringUTF(apiKey.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_abdelmageed_flickersimages_data_common_module_NetworkModule_getBaseUrl(JNIEnv *env,
                                                                                jobject thiz) {
    std::string baseURL = "https://www.flickr.com/services/rest/";
    return env->NewStringUTF(baseURL.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_abdelmageed_flickersimages_presentation_MyApp_getAppCenterSecretKey(JNIEnv *env,
                                                                             jobject thiz) {
    std::string appCenterSecretKey = "a34f6d17-48f8-4eec-bb0a-2e67d3e2ced7";
    return env->NewStringUTF(appCenterSecretKey.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_abdelmageed_flickersimages_presentation_MainActivity_getAppCenterSecretKey(JNIEnv *env,
                                                                                    jobject thiz) {
    std::string appCenterSecretKey = "a34f6d17-48f8-4eec-bb0a-2e67d3e2ced7";
    return env->NewStringUTF(appCenterSecretKey.c_str());
}