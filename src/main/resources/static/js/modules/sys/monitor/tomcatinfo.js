
var prefix = '/actuator/metrics/';

var aMetrics = [
    'tomcat.sessions.created',
    'tomcat.sessions.expired',
    'tomcat.sessions.active.current',
    'tomcat.sessions.active.max',
    'tomcat.sessions.rejected',
    'tomcat.global.sent',
    'tomcat.global.request.max',
    'tomcat.global.request',
    'http.server.requests',
    'tomcat.threads.current',
    'tomcat.threads.config.max',
];

$(function () {
    f();
    var sDate = '数据获取时间 ' + moment().format('YYYY年MM月DD日 HH时mm分ss秒');
    $("#disDate").html(sDate);
})
function f() {
    for (var j = 0, n = aMetrics.length, url; j < n; ++j) {
        url = prefix + aMetrics[j];
        $.ajax({
            async: false,
            cache: false,
            timeout: 5000,
            type: "GET",
            url: url,
            complete:ajaxComplete.bind(null, aMetrics[j])
        });
    }
}
function ajaxComplete(id, result) {
    var baseUnit = result.responseJSON.baseUnit;
    var val = result.responseJSON.measurements[0].value;
    switch (baseUnit) {
        case "bytes":
            val = Number(val / 1048576).toFixed(3);
            break;
        default:
            break;
    }
    if (id == 'tomcat.global.request'){
        $("[id='tomcat.global.request.count']").html(val);
        $("[id='tomcat.global.request.totalTime']").html(Number(result.responseJSON.measurements[1].value).toFixed(3));
    }
    if (id == 'http.server.requests'){
        $("[id='http.server.requests.count']").html(val);
        $("[id='http.server.requests.totalTime']").html(Number(result.responseJSON.measurements[1].value).toFixed(3));
        $("[id='http.server.requests.max']").html(Number(result.responseJSON.measurements[2].value).toFixed(3));
    }
    $("[id='"+id+"']").html(val);

}