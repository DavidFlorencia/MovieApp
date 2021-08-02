//
// Created by David Florencia on 01/08/21.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring
JNICALL
Java_com_dflorencia_movieapp_api_Keys_apiKey(JNIEnv *env, jobject object) {
    std::string api_key = "33d1fa5693faffec860d5568c417e32f";
    return env->NewStringUTF(api_key.c_str());
}