#!/bin/bash

# 源目录和目标目录
source_dir="/root/.jenkins/workspace/jianguan_server/ruoyi-admin/target/ruoyi-admin.jar"
target_dir="/opt/jianguan/server/ruoyi-admin.jar"
target_log="/opt/jianguan/server/ruoyi-admin.log"
backup_dir="/opt/jianguan/server/backup"


# 日期时间戳
timestamp=$(date +%Y%m%d_%H%M%S)

# 将源目录备份到备份目录
if [ "$(ls -A $target_dir)" ]; then
    mv "$target_dir" "$backup_dir/$timestamp.jar"
    echo "Source directory backed up to $backup_dir"
else
    echo "Source directory is empty. No backup created."
fi

# 复制文件到目标目录
if [ "$(ls -A $source_dir)" ]; then
    cp -r "$source_dir" "$target_dir"
    echo "copy $source_dir to $target_dir"
else
    echo "$source_dir is empty"
fi

nohup java -jar $target_dir > $target_log 2>&1 &

jps -ml