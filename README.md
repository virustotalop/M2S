# M2S
A bukkit plugin wrapper that is powered by the program binvox.

#Commands
* /m2s - Menu command/
* /m2s convert <model> - Convert a model file.
* /m2s move <schematic> - Move a schematic file to the WorldEdit folder.
* /m2s list <models/schematics> - Lists the model and schematic files.

#Supported Operating Systems

* Windows - ✓
* Linux - ✗
  * It is currently being worked on.
* Mac OS X - ✗
  * I have no system to test it on but can be done.

#What is binvox?
**Note I am not the author of binvox I am simply just creating a plugin wrapper for the program binvox.**

If you want to know minecraft specific information it can be found [here.] (http://minecraft.gamepedia.com/Programs_and_editors/Binvox)

The below is quoted from http://www.cs.princeton.edu/~min/binvox/ to give you an overview of what binvox is.

>Introduction
>
>binvox is a straight-forward program that reads a 3D model file, rasterizes it into a binary 3D voxel grid, and writes the resulting voxel file."
>Features
>"input formats:
>nearly 100% VRML 2.0 support
>will parse Wavefront OBJ, Geomview OFF, Autocad DXF, PLY and STL, if they contain polygons only
>VRML 1.0 support added in version 1.08 (still in alpha) (Unigrafix UG, VTK, XGL, PovRay POV, BREP, and JOT support temporarily disabled, let me know if you really need this)
>output formats:
>.binvox, HIPS, MIRA, VTK, a "raw" file format, minecraft .schematic format, Gmsh .msh format, and nrrd format
>runs fast if you have a current graphics card
>rasterizes to a cube grid of up to 1024x1024x1024 (if you have enough memory...)

>**Credit**

>If you use binvox for your (published) work, please add a reference to me, to this site (as a link, you could use http://www.google.com/search?q=binvox), and to the paper by F. Nooruddin and G. Turk (see next paragraph). Unfortunately I've already seen one recent scientific paper using my software without proper credit. I'd love to hear what you use binvox for as well.
>Note that binvox is free to download and use (in any environment). However, you are not allowed to charge others for the program. Type binvox -license for more information.

>Eric Haines contributed the code for the exact voxelization method.

>Qingnan Zhou wrote the code for the Gmsh .msh output.

>binvox uses the [lib3ds](http://www.lib3ds.org/) library for reading .3ds files.

>**References**

>binvox uses the parity count method and (a slight variation of) the ray stabbing method described by Fakir Nooruddin and Greg Turk in Simplification and Repair of Polygonal Models Using Volumetric Techniques, GVU technical report 99-37 (later published in IEEE Trans. on Visualization and Computer Graphics, vol. 9, nr. 2, April 2003, pages 191-205). To speed up the parity counting, a hardware z-buffer "slicing" method is used, based on an idea originally by Emil Praun.

>**Other references:**

>* Volumetric Model Repair for Virtual Reality Applications, by Andreas Kolb and Lars John, Eurographics 2001. They describe an implementation of Nooruddin and Turk's method, and convert the resulting voxel models back to 3D meshes.

>* A Fast Depth-Buffer-Based Voxelization Algorithm, by Evaggelia-Aggeliki Karabassi, Georgios Papaioannou and Theoharis Theoharis, Journal of Graphics Tools, vol. 4, nr. 4, 1999. Simple z-buffer based carving (as also supported in binvox), and a surface voxelization method.

>* Hardware Accelerated Voxelization, by Shiaofen Fang and Hongsheng Chen, Computers and Graphics, vol. 24, nr. 3, pages 433-442, June 2000.

>* Complete Polygonal Scene Voxelization, by D. Haumont and N. Warzèe, Journal of Graphics Tools, vol. 7, nr. 3, pages 27-41, 2002.

>* [vxt](http://www.viskom.oeaw.ac.at/~milos/), by Milos Sramek, is a tool for anti-aliased voxelization.

>* [Single-pass GPU solid voxelization for real-time applications](http://portal.acm.org/citation.cfm?id=1375728), by Elmar Eisemann and Xavier Décoret. They present a very fast method for voxelizing watertight models.

>Feedback

>Please send me e-mail (to patrick.n.min at gmail dot com) with your questions/comments/suggestions/bug reports. I'm also interested to hear about what you use binvox for.
>Note that because of differences in hardware and software (drivers, the OpenGL implementation on your OS, etc.) the resulting voxel model will probably differ slightly from one setup to another.
