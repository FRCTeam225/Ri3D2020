# FIRST Capital Ri3D 2020


#### Shooter tuning

Due to the amount of hands-on time needed to improve the intake, shooter tuning took a backseat. I'm pretty confident with a little more work we could have been hitting more shots [like this one](https://www.twitch.tv/firstupdatesnow/clip/ElegantMuddyMangetoutBloodTrail).

To make this happen we would:

1) Handle the offset limelight from the shooter. Some trig is required to center yourself on the goal from any distance.

2) Pick RPM from distance (i.e. [our 2017 code](https://github.com/FRCTeam225/Robot2017/blob/master/src/org/techfire225/robot/Constants.java#L126))

3) Handle target skew

All of our shots in Ri3D were run at a constant RPM with the limelight offset tuned at a fixed distance.

#### Other thoughts

- Our code quality isn't great - quick hacks are an unfortunate reality of Ri3D. For your work in-season, check out the WPILib command framework.

- We used a custom dashboard for tuning, but you can easily accomplish the same with the SPARK MAX client or Shuffleboard. Check out [Oblog](https://oblog-docs.readthedocs.io/en/latest/introduction.html) for a library that makes interfacing with Shuffleboard a little cleaner.
