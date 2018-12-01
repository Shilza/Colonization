let gulp = require('gulp'),
    concat = require('gulp-concat'),
    uglify = require('gulp-uglifyjs');

gulp.task('watch', function() {
    gulp.watch('src/main/js/**/*.js', ['scripts']);
});

gulp.task('scripts', function() {
    return gulp.src('src/main/js/**/*.js')
        .pipe(concat('app.js'))
        //.pipe(uglify())
        .pipe(gulp.dest('public/'));
});