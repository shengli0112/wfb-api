
#!/bin/bash
target_dir='/workspace/prj/xianbao/Xshop/xshop-api-java'
source_dir='/Users/shc/Desktop/xianbao_code'
modules_name='seckill'

#逻辑源码目录
target_src_dir=${target_dir}"/xianbao-admin-console/src/main/java/shop/xianbao/modules"
source_src_dir=${source_dir}"/main/java/shop/xianbao/modules"

#实体源码目录
target_entity_dir=${target_dir}"/xianbao-entity/src/main/java/shop/xianbao/modules"

#资源文件目录
target_resources_dir=${target_dir}"/xianbao-admin-console/src/main/resources/mapper"
src_resources_dir=${source_dir}"/main/resources/mapper"


#copy entity
copy_entity(){
    #entity
    cd $target_entity_dir
    if [ ! -d ${modules_name} ];then
        mkdir ${modules_name}
    fi
    cp -R -f ${source_src_dir}"/"${modules_name}"/entity" ${target_entity_dir}"/"${modules_name}
    echo 'copy entity code success.'
}

#copy admin-api
copy_admin_api(){
    #src
    cd $target_src_dir
    if [ ! -d ${modules_name} ];
    then
        mkdir ${modules_name}
    fi
    cd $modules_name
    cp -R -f ${source_src_dir}"/"${modules_name}"/controller" ./
    cp -R -f ${source_src_dir}"/"${modules_name}"/dto" ./
    cp -R -f ${source_src_dir}"/"${modules_name}"/service" ./
    cp -R -f ${source_src_dir}"/"${modules_name}"/dao" ./

    #resources
    cd $target_resources_dir
    if [ ! -d ${modules_name} ];
    then
        mkdir ${modules_name}
    fi
    cp -R -f ${src_resources_dir}"/${modules_name}" ./
    echo 'copy admin api success.'
}

#商家端管理后台接口 seller-api

copy_seller_api(){
    cd '/workspace/prj/xianbao/Xshop/xshop-api-java/xianbao-shop-console/src/main/java/shop/xianbao/modules'
    if [ ! -d ${modules_name} ];
    then
        mkdir ${modules_name}
    fi
    cd $modules_name

    cp -R -f ${source_src_dir}"/"${modules_name}"/controller" ./
    cp -R -f ${source_src_dir}"/"${modules_name}"/dto" ./
    cp -R -f ${source_src_dir}"/"${modules_name}"/service" ./
    cp -R -f ${source_src_dir}"/"${modules_name}"/dao" ./

    cd '/workspace/prj/xianbao/Xshop/xshop-api-java/xianbao-shop-console/src/main/resources/mapper'
    if [ ! -d ${modules_name} ];
    then
        mkdir ${modules_name}
    fi
    cd $modules_name
    cp -R -f ${src_resources_dir}"/${modules_name}" ./
    echo 'copy seller api success.'
}

#前端代码 admin
copy_admin_vue(){
    cd "/workspace/prj/xianbao/Xshop/xshop-admin-vue/src/views/modules"
    cp -r -f "/Users/shc/Desktop/xianbao_code/vue/views/modules/"${modules_name} ./
    echo 'admin vue copy success.'
}

#copy 前端代码 seller
copy_seller_vue(){
    cd /workspace/prj/xianbao/weifangbao/xshop-seller-vue/src/views/modules
    cp -r -f "/Users/shc/Desktop/xianbao_code/vue/views/modules/"${modules_name} ./
    echo 'seller vue copy success.'
}

# 生成代码逻辑
echo "Enter the number to copy the corresponding source code:
      0、all
      1、seller-vue
      2、admin-vue
      3、seller-api
      4、admin-api
      5、entity"
read Arg
case $Arg in
    1)
        copy_seller_vue;;
    2)
        copy_admin_vue;;
    3)
        copy_seller_api;;
    4)
        break;;
    5)
        break;;
    all)
    break;
esac