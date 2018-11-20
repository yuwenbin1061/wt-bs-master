var table;

//初始化
function initTable() {

    // #######不用动begin#########
    table = $('#table').bootstrapTable({
        sidePagination: "server", //表格分页的位置,必选
        method: 'post',//必选
        contentType: 'application/x-www-form-urlencoded; charset=UTF-8',//编码,必选
        pagination: true, //启动分页
        pageSize: 10, //每页显示的记录数
        pageNumber: 1, //当前第几页
        pageList: [10, 20, 30], //记录数可选列表
        striped: true,
        clickToSelect: true,//点击行即可选中单选/复选框
        formatLoadingMessage: function () {
            return "少々お待ちください...";
        },
        formatNoMatches: function () {  //没有匹配的结果
            return '該当する記録はありません';
        },
        url: "/papers/getList", //必选
        queryParams: queryParams, //参数
        responseHandler : function(result) {
            if(result.code != "0000") {
                alert("検索失敗");
            }
            return result.data;
        },
        columns: [
            {
                field: 'paperName',
                title: '试卷名',
                align: 'center',
                valign: 'middle'
            },
            {
                field: 'teacherName',
                title: '教師名',
                align: 'center',
                valign: 'middle'
            },
            {
                field: 'id',
                title: '操作',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row) {
                    const batchNo = row['batchNo'];
                    const problems = '<a  href="/problems/list?batchNo=' + batchNo + '" class="am-btn am-btn-default am-btn-xs am-text-secondary"><i class="am-icon-pencil-square-o"></i>詳細を見る</a>';

                    return problems
                }
            }
        ]
    });
}


//查询条件
function queryParams(params) {
    params.batchNo = $("#batchNo").val();
    params.paperName = $("#paperName").val();
    return params;
}

// 查询
function find(){
    $("#table").bootstrapTable('selectPage', 1).bootstrapTable("refresh");
}


