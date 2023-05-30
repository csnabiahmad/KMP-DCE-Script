package model

import com.google.gson.annotations.SerializedName


class TeacherTrainingVideoModel : ArrayList<TeacherTrainingVideoModel.TeacherTrainingVideoModelItem>(){
    data class TeacherTrainingVideoModelItem(
        @SerializedName("video_link")
        var videoLink: String // https://teacher-training-videos.s3.me-south-1.amazonaws.com/Taleemabad's+Philosophy.mp4
    )
}