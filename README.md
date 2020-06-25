# 使用 Activity Result API + Kotlin 扩展函数 封装权限请求库

## 引入

[![](https://jitpack.io/v/Flywith24/Flywith24-Permission.svg)](https://jitpack.io/#Flywith24/Flywith24-Permission)

1. 在项目根目录的 `build.gradle` 加入

   ``` groovy
   allprojects {
   		repositories {
   			...
   			maven { url 'https://jitpack.io' }
   		}
   	}
   ```

   

2. 添加依赖

   ``` groovy
   dependencies {
	  implementation 'com.github.Flywith24:Flywith24-Permission:$version'
	}
   ```



## Activity Result API

在  Android Jetpack `Activity 1.2.0-alpha02` 和 `Fragment 1.3.0-alpha02` 中，Google 提供了全新的 **Activity Result API** 来替换 `startActivityForResult()`+`onActivityResult()`和`requestPermissions()`+`onRequestPermissionsResult()`。详情可移步[官方文档](https://developer.android.com/training/basics/intents/result)



## requestPermissions() / onRequestPermissionsResult() 被弃用

紧接着在 `Activity 1.2.0-alpha04` 和 `Fragment 1.3.0-alpha04` 版本中，

`startActivityForResult()`+`onActivityResult()`和`requestPermissions()`+`onRequestPermissionsResult()`被标记为弃用，而在`Fragment 1.3.0-alpha05` 这些标记弃用的方法内部已改为使用 `ActivityResultRegistry` 实现

![](https://gitee.com/flywith24/Album/raw/master/img/20200625201515.png)

![](https://gitee.com/flywith24/Album/raw/master/img/20200625201449.png)

## 新 API 的使用

新的 API 使用非常简单，分为单一权限请求，和多权限请求，Activity 和 Fragment 使用方法相同



### 单一权限请求

``` kotlin
val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE
registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
// 请求结果，result 为 boolean true 代表已授权，false 代表未授权       
}.launch(permission)
```



### 多权限请求

``` kotlin
val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)

registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result: MutableMap<String, Boolean> ->
// 请求结果，返回一个map ，其中 key 为权限名称，value 为是否权限是否赋予
}.launch(permissions)
```



## 配合 Kotlin 扩展函数进行封装

配合 Kotlin 的扩展函数，我们可以将权限请求的逻辑进行封装。

开发过程中，我们申请权限时关注的就是权限是否申请成功，如果未申请成功是否勾选了不再询问



因此我们可以加入「权限申请成功」，「权限申请失败且未勾选不再询问」，「权限申请失败且已勾选不再询问」三种状态的回调

``` kotlin
/**
 * [permission] 权限名称
 * [granted] 申请成功
 * [denied] 被拒绝且未勾选不再询问
 * [explained] 被拒绝且勾选不再询问
 */
inline fun Fragment.requestPermission(
    permission: String,
    crossinline granted: (permission: String) -> Unit = {},
    crossinline denied: (permission: String) -> Unit = {},
    crossinline explained: (permission: String) -> Unit = {}

) {
    registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        when {
            result -> granted.invoke(permission)
            shouldShowRequestPermissionRationale(permission) -> denied.invoke(permission)
            else -> explained.invoke(permission)
        }
    }.launch(permission)
}

/**
 * [permissions] 权限数组
 * [allGranted] 所有权限均申请成功
 * [denied] 被拒绝且未勾选不再询问，同时被拒绝且未勾选不再询问的权限列表
 * [explained] 被拒绝且勾选不再询问，同时被拒绝且勾选不再询问的权限列表
 */
inline fun Fragment.requestMultiplePermissions(
    vararg permissions: String,
    crossinline allGranted: () -> Unit = {},
    crossinline denied: (List<String>) -> Unit = {},
    crossinline explained: (List<String>) -> Unit = {}
) {
    registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result: MutableMap<String, Boolean> ->
        //过滤 value 为 false 的元素并转换为 list
        val deniedList = result.filter { !it.value }.map { it.key }
        when {
            deniedList.isNotEmpty() -> {
                //对被拒绝全选列表进行分组，分组条件为是否勾选不再询问
                val map = deniedList.groupBy { permission ->
                    if (shouldShowRequestPermissionRationale(permission)) DENIED else EXPLAINED
                }
                //被拒接且没勾选不再询问
                map[DENIED]?.let { denied.invoke(it) }
                //被拒接且勾选不再询问
                map[EXPLAINED]?.let { explained.invoke(it) }
            }
            else -> allGranted.invoke()
        }
    }.launch(permissions)
}
```



使用起来是这样的

### 单一权限申请

``` kotlin
requestPermission(Manifest.permission.RECORD_AUDIO,
    granted = { permission ->
        //权限申请成功
        },
    denied = { permission ->
       //权限申请失败且未勾选不再询问，下次可继续申请
    },
    explained = { permission ->
        //权限申请失败且已勾选不再询问，需要向用户解释原因并引导用户开启权限
    })
```



### 多权限申请

``` kotlin
requestMultiplePermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA,
    allGranted = {
        //全部权限均已申请成功
    },
    denied = {list->
        //权限申请失败且未勾选不再询问，下次可继续申请
    },
    explained = {list->
        //权限申请失败且已勾选不再询问，需要向用户解释原因并引导用户开启权限
    })
```



## Java 版本

Java 是可以调用 Kotlin 的扩展函数的，为了跟方便地调用，可以在此基础上再封装一层



### 单一权限请求

``` java
PermissionUtils.requestPermission(this, permission, new PermissionResultListener() {
    @Override
    public void granted(String permission) {
        Log.i(TAG, "granted: ");
    }
    @Override
    public void denied(String permission) {
        Log.i(TAG, "denied: ");
    }
    @Override
    public void explained(String permission) {
        Log.i(TAG, "explained: ");
    }
});
```



### 多权限请求

``` java
PermissionUtils.requestMultiplePermissions(this, new MultiPermissionResultListener() {
    @Override
    public void allGranted() {
        Log.i(TAG, "allGranted: ");
    }
    @Override
    public void denied(List<String> list) {
        Log.i(TAG, "denied: " + list.toString());
    }
    @Override
    public void explained(List<String> list) {
        Log.i(TAG, "explained: " + list.toString());
    }
}, permissions);
```

