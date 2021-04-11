package com.harishb2k;


import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;

public class FileEdit {
    private static String dir;
    private static String pack;
    private static String groupId;
    private static String artifactId;
    private static String version;
    private static String project;

    public static void main(String[] args) {
        Options options = new Options();
        Option dir = new Option("d", "dir", true, "dir to process");
        dir.setRequired(true);
        options.addOption(dir);
        Option pack = new Option("p", "package", true, "package");
        pack.setRequired(true);
        options.addOption(pack);
        Option artifactId = new Option("a", "artifactId", true, "artifactId");
        artifactId.setRequired(true);
        options.addOption(artifactId);
        Option groupId = new Option("g", "groupId", true, "groupId");
        groupId.setRequired(true);
        options.addOption(groupId);
        Option project = new Option("pr", "project", true, "project");
        project.setRequired(true);
        options.addOption(project);
        Option version = new Option("v", "version", true, "version");
        version.setRequired(true);
        options.addOption(version);
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
        FileEdit.dir = cmd.getOptionValue("d");
        FileEdit.pack = cmd.getOptionValue("p");
        FileEdit.artifactId = cmd.getOptionValue("a");
        FileEdit.groupId = cmd.getOptionValue("g");
        FileEdit.project = cmd.getOptionValue("pr");
        FileEdit.version = cmd.getOptionValue("v");


        FileEdit app = new FileEdit();
        app.iterate(FileEdit.dir, new FileProcessor() {
            @Override
            public void process(File file) {
                if (file.getName().endsWith("pom.xml")) {
                    app.processPomFile(file);
                } else {
                    app.processJavaFile(file);
                }
            }
        });
    }

    public void iterate(String dir, FileProcessor fileProcessor) {
        FileUtils.listFiles(new File(dir), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE).forEach(file -> {
            if (!file.isFile()) return;
            String fileName = file.getName();
            if (Objects.equals("pom.xml", fileName) || fileName.endsWith(".java") || fileName.endsWith(".proto")) {
                fileProcessor.process(file);
            }
        });
    }

    private void processPomFile(File file) {
        try {
            String content = IOUtils.toString(file.toURI(), Charset.defaultCharset());
            content = content.replace("<groupId>" + FileEdit.groupId + "</groupId>", "<groupId>${groupId}</groupId>");
            content = content.replace("<version>" + FileEdit.version + "</version>", "<version>${version}</version>");
            if (content.contains("modules")) {
                content = content.replace("<artifactId>" + FileEdit.artifactId + "</artifactId>", "<artifactId>${artifactId}</artifactId>");
                content = content.replace("<mainClass>" + FileEdit.pack + ".app.RestApplication</mainClass>", "<mainClass>${groupId}.app.Application</mainClass>");
            } else {
                content = content.replace("<artifactId>" + FileEdit.artifactId + "</artifactId>", "<artifactId>${rootArtifactId}</artifactId>");
            }
            rewriteFile(file, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processJavaFile(File file) {
        try {
            String content = IOUtils.toString(file.toURI(), Charset.defaultCharset());
            content = content.replace(FileEdit.pack, "${package}");
            rewriteFile(file, content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void rewriteFile(File file, String content) throws IOException {
        FileUtils.write(file, content, Charset.defaultCharset());
    }

    interface FileProcessor {
        void process(File file);
    }
}
