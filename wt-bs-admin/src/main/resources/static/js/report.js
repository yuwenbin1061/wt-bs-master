var table;

//初始化
function initTable(tableId, action) {

    // #######不用动begin#########
    table = $(tableId).bootstrapTable({
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
        url: "/report/" + action, //必选
        queryParams: queryParams, //参数
        responseHandler: function (result) {
            if (result.code != "0000") {
                alert("検索失敗");
            }
            return result.data;
        },
        columns: [
            // [
            //     {
            //         // field: 'descs',
            //         title: 'テキスト',
            //         align: 'left',
            //         // valign: 'middle'
            //     },
            //     {
            //         field:'descs',
            //         colspan :7,
            //     }
            // ],
            // [
            {
                title: '番号',
                align: 'center',
                valign: 'middle',
                formatter: function (value, row, index) {
                    return index + 1;
                }
            },
            {
                field: 'answer',
                title: '解答',
                align: 'center',
                valign: 'middle'
            },
            {
                field: 'count',
                title: '人数',
                align: 'center',
                valign: 'middle',
            },
            {
                field: 'stdName',
                title: '学生名',
                align: 'center',
                valign: 'middle',
            },
        ]

        // ]
    });
}


//查询条件
function queryParams(params) {
    params.problemId = $("#problemId").val();
    return params;
}

// 查询
function find() {
    $("#table").bootstrapTable('selectPage', 1).bootstrapTable("refresh");
}


// 答案添加
function addProblem() {
    $.ajax({
        type: "POST",
        async: false,
        data: $('#answerFrom').serialize(),
        dataType: 'json',
        url: "/answer/save",
        success: function (data) {
            if (data.code == '0000') {
                alert("解答が保存されました！");
                window.location.href = "/problems/list";
            } else {
                alert(data.msg);
            }
        }
    });
}
