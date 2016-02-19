/**
 * Notes util
 */
function showNotesBox(note_url, obj_id, obj_type, page_id) {
	$
			.ajax({
				url : note_url + '/' + obj_type + '/' + obj_id + '/' + page_id
						+ '/showNotes',
				statusCode : {
					200 : function(data) {
						var len = data.length;
						var notesHtml = '';
						for (var i = 0; i < len; i++) {
							notesHtml = notesHtml
									+ '<div class="noteUserInfoContainer">';
							notesHtml = notesHtml + '<span class="noteUser">'
									+ data[i].user + '</span>&nbsp;';
							notesHtml = notesHtml
									+ '<span class="noteAddTime">added a comment</span>';
							notesHtml = notesHtml
									+ '<span class="noteAddTime"> - '
									+ data[i].formattedDate + '</span>';
							notesHtml = notesHtml + '</div>';
							notesHtml = notesHtml
									+ '<div class="eachNote" id="axNoteText">';
							notesHtml = notesHtml + data[i].notes;
							notesHtml = notesHtml + '</div>';
						}
						$('#axNotesContainer').html(notesHtml);
					}
				}
			});
}
function showNoteEntry(note_url, obj_id, obj_type, page_id, user_id) {
	var parameters = {
		objectId : obj_id,
		objectType : obj_type,
		pageId : page_id,
		userId: user_id
	};
	$.ajax({
		url : note_url,
		data : parameters,
		statusCode : {
			200 : function(data) {
				$('#axNoteBox').html(data);
			}
		}
	});
}

$(document).ready(function() {
	setupNotes();
});

function setupNotes() {
	var this_js_script = $('script[src*=notes]');
	var note_url = this_js_script.attr('note_url');
	var obj_id = this_js_script.attr('obj_id');
	var obj_type = this_js_script.attr('obj_type');
	var page_id = this_js_script.attr('page_id');
	var user_id = this_js_script.attr('user_id');
	showNotesBox(note_url, obj_id, obj_type, page_id);
	showNoteEntry(note_url, obj_id, obj_type, page_id, user_id);
}

$(document).on("click", '#saveNote', function(e) {
	e.preventDefault();
	var noteUrl = $('#addNoteUrl').val();
	var notesText = $('#notesText').val();
	addNote(noteUrl, notesText);
});

function addNote(note_url, notes_text) {
	var parameters = {
		notesText : notes_text
	};
	$.ajax({
		url : note_url,
		data : parameters,
		type : "POST",
		dataType : "json",
		success : function() {
			setupNotes();
		}
	});
}