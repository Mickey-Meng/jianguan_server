#!/bin/sh
jps -ml
jps -ml| grep 'ruoyi-admin' | awk '{print $1}' | xargs --no-run-if-empty kill
