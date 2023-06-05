package model

import com.google.gson.annotations.SerializedName


class TeacherTrainingVideoModel : ArrayList<TeacherTrainingVideoModel.TeacherTrainingVideoModelItem>(){
    data class TeacherTrainingVideoModelItem(
        @SerializedName("uuid")
        var uuid: String, // 06f09762-527d-41bb-ac6c-628b73d896f4
        @SerializedName("video_link")
        var videoLink: String // https://teacher-training-videos.s3.me-south-1.amazonaws.com/Taleemabad's+Philosophy.mp4
    )
}