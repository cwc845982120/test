var gulp = require('gulp')
var rev = require('gulp-rev')
var concat = require('gulp-concat')
var minifyCss = require('gulp-minify-css')

gulp.task('default', function() {
    gulp.src(['src/**/*.css','!src/js/**'])
    .pipe(minifyCss())                         //- 压缩处理成一行
    .pipe(rev())                              //- 文件名加MD5后缀
    .pipe(gulp.dest('./dist/css'))                //- 输出文件本地
    .pipe(rev.manifest())                     //- 生成一个rev-manifest.json
    .pipe(gulp.dest('./rev'));                  //- 将 rev-manifest.json 保存到 rev 目录内
});