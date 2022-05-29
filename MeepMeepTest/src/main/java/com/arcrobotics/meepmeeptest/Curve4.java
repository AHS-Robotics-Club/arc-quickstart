package com.arcrobotics.meepmeeptest;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import org.graalvm.compiler.lir.LIRInstruction;

public class Curve4 {
    public static void main(String[] args){
        MeepMeep meepMeep = new MeepMeep(600);

        RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep)
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                        .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(48, -36, Math.toRadians(90)))
                                        .lineTo(new Vector2d(48, 12))
                                        .splineTo(new Vector2d(20, 12), Math.toRadians(270))
                                        .lineTo(new Vector2d(20, -48))
                                        .splineTo(new Vector2d(8, 0), Math.toRadians(45))
                                        .splineTo(new Vector2d(0, 8), Math.toRadians(180))
                                        .lineTo(new Vector2d(-48, 8))
                                        .build()
                        );

        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(bot)
                .start();
    }
}
