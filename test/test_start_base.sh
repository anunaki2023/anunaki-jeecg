#!/bin/bash

project_dir=$(cd $(dirname $0); pwd)/../
realtime_conf_path="$project_dir/jeecg-module-system/jeecg-system-start/target/config"
realtime_lib_dir="$project_dir/jeecg-module-system/jeecg-system-start/target/lib"
loader_path="$realtime_conf_path,$realtime_lib_dir,"

# -Dloader.path 指定外部依赖的 jar
# jvm 参数是右优先，后覆盖的
# 注意 spring.config.location 指定目录的话要记得加上 /
java \
  -Dlog4j2.formatMsgNoLookups=true \
  -Dloader.path="$loader_path" \
  -jar jeecg-module-system/jeecg-system-start/target/jeecg-system-start-3.4.4.jar
