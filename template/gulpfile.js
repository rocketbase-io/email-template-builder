var gulp = require('gulp'),
  inlineCss = require('gulp-inline-css')
juice = require('gulp-juice')

gulp.task('default', function () {
  return gulp.src('./src/*')
    .pipe(inlineCss({
      removeLinkTags: false,
      removeStyleTags: false,
      preserveMediaQueries: true,
      applyWidthAttributes: true,
      applyTableAttributes: true,
      codeBlocks: {EJS: {start: '<%', end: '%>'}, HBS: {start: '{{', end: '}}'}, PEBBLE: {start: '{%', end: '%}'}}
    }))
    .pipe(gulp.dest('../email-template-builder/src/main/resources/templates/email/'))
})