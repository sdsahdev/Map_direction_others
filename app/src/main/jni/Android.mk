#LOCAL_PATH := $(call my-dir)
#
#include $(CLEAR_VARS)
#LOCAL_MODULE    := keys
#LOCAL_SRC_FILES := keys.c
#include $(PREBUILT_SHARED_LIBRARY)
#
#include $(CLEAR_VARS)
#LOCAL_MODULE    := final_keys
#LOCAL_SRC_FILES := keys.c
#LOCAL_LDLIBS := -L$(SYSROOT)/usr/lib -llog
#LOCAL_PREBUILTS := libs/ffmpeg.so
#include $(BUILD_SHARED_LIBRARY)
##libavformat libavcodec libswscale libavutil
#
##LOCAL_PATH := $(call my-dir)
##include $(CLEAR_VARS)
##LOCAL_MODULE := api-keys
##LOCAL_SRC_FILES := api-keys.c
##include $(BUILD_SHARED_LIBRARY)
#
#
#
#LOCAL_PATH := $(call my-dir)
#
#include $(CLEAR_VARS)
#LOCAL_MODULE    := cppTestProj
#LOCAL_SRC_FILES := maintestapp.cpp \
#                   Test_array_type.cpp
#
#include $(BUILD_SHARED_LIBRARY)
#

LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE    := keys
LOCAL_SRC_FILES := keys.c

include $(BUILD_SHARED_LIBRARY)