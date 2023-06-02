#include <jni.h>
JNIEXPORT jstring JNICALL
Java_com_example_array_MainActivity_getFacebookApiKey(JNIEnv* env, jobject thiz) {
    return (*env)->  NewStringUTF(env, " domadiya");
}
