package com.kiran.directoryViewer.model.fetcher;

import com.kiran.directoryViewer.model.CurrentViewDiskResidentAware;
import com.kiran.directoryViewer.model.DiskResident;
import com.kiran.patterns.subscription.SubscriptionManager;

import java.nio.file.AccessDeniedException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kiran Kolli on 30-12-2016.
 */
public class DirectoryStructureManager implements CurrentViewDiskResidentAware, NodeNavigationCapable {

    private final SubscriptionManager<CurrentDirectoryResidentListener> subscriptionManager =
            new SubscriptionManager<>();
    private final List<DiskResident> currentDirectoryResidents = new ArrayList<>();

    @Override
    public DirectoryStructureNode gotoRootNode() {
        DirectoryStructureNode root = DirectoryDetailCache.getRootNode();
        setCurrentResidents(root);
        return DirectoryDetailCache.getRootNode();
    }

    @Override
    public DirectoryStructureNode gotoNode(Path absolutePath) {
        try {
            DirectoryStructureNode node = DirectoryDetailCache.gotoNode(absolutePath);
            setCurrentResidents(node);
            return node;
        } catch (NoSuchFileException | AccessDeniedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void forceCreateNode(Path absolutePath) {

    }

    @Override
    public void forceCreateNodeFullDepth(Path absolutePath) {

    }

    @Override
    public Object registerListener(CurrentDirectoryResidentListener listener) {
        Object handle;
        synchronized (subscriptionManager) {
            handle = subscriptionManager.subscribe(listener);
        }
        listener.onChange(new ArrayList<>(currentDirectoryResidents));
        return handle;
    }

    @Override
    public void unregister(Object handle) {
        synchronized (subscriptionManager) {
            subscriptionManager.unsubscribe(handle);
        }
    }

    private void setCurrentResidents(DirectoryStructureNode node) {
        synchronized (subscriptionManager) {
            currentDirectoryResidents.clear();
            node.addChildrenTo(currentDirectoryResidents);
            subscriptionManager.invokeOnAllSubscribers(currentDirectoryResidentListener ->
                    currentDirectoryResidentListener.onChange(currentDirectoryResidents));
        }
    }
}
