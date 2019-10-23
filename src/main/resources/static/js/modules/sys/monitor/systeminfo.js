
var prefix = '/actuator/metrics/';

var aMetrics = [
    'system.cpu.count',
    'system.cpu.usage',
    'process.uptime',
    'process.start.time',
    'process.cpu.usage',
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
    var val = result.responseJSON.measurements[0].value;
    switch (id) {
        case "system.cpu.usage":
        case "process.cpu.usage":
            val = Number(val * 100).toFixed(2);
            break;
        case "process.start.time":
            val = moment(val * 1000).format('YYYY-MM-DD HH:mm:ss');
            break;
        default:
            break;
    }
    $("[id='"+id+"']").html(val);

}