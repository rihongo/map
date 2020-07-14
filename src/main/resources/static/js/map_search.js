"use strict";
let MapSearch = (function () {

    let keyword = '', isSaved = false;

    const init = function () {
        $("input[name=search]").on('click', searchMap);
        $("input[name=refreshTopKeyword]").on('click', getTopKeyword);
        getTopKeyword();
    };

    const getTopKeyword = function () {
        $.ajax({
            url: '/map/top/keyword',
            type: 'get',
            success: function (res) {
                let dataHtml = '';

                if (res.data.length === 0) {
                    dataHtml += '<span>검색 기록 없습니다.</span>';
                    isSaved = true;
                } else {
                    $.each(res.data, function (index, item) {
                        dataHtml +=
                            '<li class="list-group-item list-group-item-warning">' +
                            '<p> 검색어 - ' + item.keyword + '</p>' +
                            '<p> 검색수 - ' + item.count + '</p>' +
                            '</li>';
                    });
                }
                $("#top-data-container").html(dataHtml);

            }, error: function (res) {
                console.log(res);
            }
        });
    }

    const searchMap = function () {
        keyword = $("input[name=query]").val();
        if (keyword === '') {
            alert("검색어를 입력해주세요");
            return;
        }
        isSaved = false;
        let container = $('#pagination-container');
        container.pagination({
            className: 'paginationjs-theme-yellow paginationjs-big',
            dataSource: '/map/search?query=' + keyword,
            locator: function () {
                return 'data.documents';
            },
            totalNumberLocator: function (response) {
                return response.data.meta.pageable_count;
            },
            alias: {
                pageNumber: 'page',
                pageSize: 'size',
            },
            ajax: {
                beforeSend: function () {
                    container.prev().html('검색중 입니다.');
                },
                type: 'get',
                success: function (response) {
                    done(response.data.documents, response.data.meta);
                }
            },
            formatAjaxError: function (response) {
                alert(response["responseJSON"]["message"]);
            },
            callback: function (response) {
                let dataHtml = '<div class="list-group" style="margin-bottom: 15px;">';

                if (response.length === 0) {
                    dataHtml += '<span>검색결과가 없습니다.</span>';
                    isSaved = true;
                } else {
                    $.each(response, function (index, item) {
                        dataHtml += '<a href=' + item.shortcuts + ' class="list-group-item">';
                        dataHtml += '<h4>' + item.place_name + '</h4>';
                        dataHtml += '<p class="text-muted">' + item.category_name + '</p>';
                        dataHtml += '<p class="text-primary"> ' + item.road_address_name + '</p>';
                        dataHtml += '<p class="text-muted">(지번) ' + item.address_name + '</p>';
                        dataHtml += '<p class="text-success"> ' + item.phone + '</p>';
                        dataHtml += '</a>';
                    });
                    dataHtml += '</div>';
                }
                container.prev().html(dataHtml);

                if (!isSaved) {
                    saveSearchKeyword();
                }
            }
        })
    }

    const saveSearchKeyword = function () {
        $.ajax({
            url: '/map/top/keyword',
            data: {
                'keyword': keyword
            },
            type: 'post',
            success: function () {
                isSaved = true;
            }, error: function (res) {
                console.log(res);
            }
        });
    }

    return {
        init: init
    };

})();

MapSearch.init();
